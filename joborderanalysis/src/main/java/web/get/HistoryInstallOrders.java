package web.get;

import base.FileUtils;
import base.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import web.browsers.Browser;
import web.browsers.IeBrowser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class HistoryInstallOrders extends DispatchSystem implements WebSystem {

    private static final String CLICK = "arguments[0].click()";//单击操作 xpath
    private static final String HISTORY_ORDERS = "//tr[@title = '03-省内装移机历史工单明细表']/td[3]/img";//历史工单明细表
    private static final String START_TIME_XPATH = "//input[@lable = '归档时间'][1]";
    private static final String END_TIME_XPATH = "//input[@lable = '归档时间'][3]";
    private static final String LOCAL_NETWORK = "//input[@lable = '本地网'][2]";
    private static final String LOCAL_NETWORK_L1_XPATH = "//tr[@title = '中国电信吉林公司']/td[@class = 'standartTreeImage'][1]/img";
    private static final String LOCAL_NETWORK_L2_XPATH = "//tr[@title = '中国电信吉林分公司']/td[@class = 'standartTreeImage'][2]/img";
    private String url;
    private String driverPath;
    private String username;
    private String password;
    private String savePath;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * 爬取客调系统历史数据方法
     *
     * @return boolean
     */
    @Override
    public boolean getData(String startTime, String endTime) {
        Browser browser = new IeBrowser(driverPath);//初始化浏览器
        WebDriver bro = browser.getBro();
        JavascriptExecutor broJs = browser.getBroJs();
        if (null == bro || null == broJs) {
            return false;
        }
        //login
        if (!super.login(browser, url, username, password)) {
            return false;
        }
        //进入报表平台
        if (!super.gotoReportForms(bro, broJs)) {
            Log.error("进入报表平台失败");
            return false;
        }
        if (!super.gotoUserReportFroms(bro, broJs)) {
            Log.error("进入用户层报表失败");
            return false;
        }
        if (!super.gotoUserReportFromsDetail(bro, broJs)) {
            Log.error("进入用户层明细表失败");
            return false;
        }
        if (!gotoHisOrders(bro, broJs)) {
            Log.error("省内装移机历史工单明细表失败");
        }
        //查询工单循环获取工单内容
        int pageIndex = selectOrders(bro, broJs, startTime, endTime);
        if (0 == pageIndex) {
            Log.error("查询历史工单明细表失败");
            return false;
        } else {
            Log.info("执行获取每页工单,按页保存");
            List<String> list = getOrders(bro, broJs, pageIndex);
            Log.info("写入数据");
            FileUtils fileUtils = new FileUtils();
            String fileName = startTime + "_" + endTime;
            Log.info(fileName);
            for (int i = 0; i < list.size(); i++) {
                String filePath = savePath + fileName + "_" + i + ".txt";
                fileUtils.createFile(filePath);
                fileUtils.wrStr2File(bro.getPageSource(), filePath);
            }
        }
        //关闭浏览器
        bro.close();
        return true;
    }




    /**
     * 进入省内装移机历史工单明细表
     *
     * @return boolean
     */

    private boolean gotoHisOrders(WebDriver bro, JavascriptExecutor broJs) {
        Log.info("进入省内装移机历史工单明细表");

        try {
            WebElement webElement = bro.findElement(By.xpath(HISTORY_ORDERS));
            broJs.executeScript(CLICK, webElement);
            Log.sleep(3);
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("worktable");
            bro.switchTo().frame("workarea");
            Log.sleep(3);

        } catch (Exception e) {
            Log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 查询历史工单
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 页数
     */
    private int selectOrders(WebDriver bro,JavascriptExecutor broJs,String startTime, String endTime){
        Log.info("查询工单明细表");
        int pageIndex ;
        WebElement webElement = bro.findElement(By.xpath(START_TIME_XPATH));//定位开始时间
        broJs.executeScript("arguments[0].removeAttribute('readOnly')", webElement);//修改时间属性为可变更
        webElement.clear();
        webElement.sendKeys(startTime);
        webElement = bro.findElement(By.xpath(END_TIME_XPATH));//定位结束时间
        broJs.executeScript("arguments[0].removeAttribute('readOnly')", webElement);//修改时间属性为可变更
        webElement.clear();
        webElement.sendKeys(endTime);
        Log.sleep(3);
        webElement = bro.findElement(By.xpath(LOCAL_NETWORK));//定位本地网
        broJs.executeScript(CLICK, webElement);
        Log.sleep(3);
        webElement = bro.findElement(By.xpath(LOCAL_NETWORK_L1_XPATH));//本地网选择
        broJs.executeScript(CLICK, webElement);
        Log.sleep(3);
        webElement = bro.findElement(By.xpath(LOCAL_NETWORK_L2_XPATH));//本地网选择
        broJs.executeScript(CLICK, webElement);
        Log.sleep(3);
        //获取所有按钮
        List<WebElement> buttonList = bro.findElements(By.tagName("BUTTON"));
        //本地网确定按钮
        webElement = buttonList.get(11);
        broJs.executeScript(CLICK, webElement);
        Log.sleep(3);
        //生成报表按钮
        webElement = buttonList.get(0);
        broJs.executeScript(CLICK, webElement);
        Log.sleep(10);
        //获取页面数量
        String pageHtml = bro.getPageSource();
        String pageNumber = pageHtml.substring(pageHtml.indexOf("共计") + 2, pageHtml.indexOf("页;转到第"));
        pageIndex = Integer.valueOf(pageNumber);
        return pageIndex;
    }


    /**
     * 进入每个页面下载文件
     *
     * @param pageIndex 页索引
     */
    private List<String> getOrders(WebDriver bro,JavascriptExecutor broJs,int pageIndex) {
        Log.info("下载工单");
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= pageIndex; i++) {
            WebElement webElement = bro.findElement(By.name("skippage"));
            webElement.clear();
            webElement.sendKeys(String.valueOf(i));
            Log.sleep(5);
            webElement = bro.findElement(By.className("button"));
            broJs.executeScript(CLICK, webElement);
            Log.sleep(10);
            list.add(bro.getPageSource());
        }
        return list;
    }
}