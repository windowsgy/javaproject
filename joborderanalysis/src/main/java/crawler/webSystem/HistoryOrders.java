package crawler.webSystem;

import base.Log;
import crawler.browsers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jlgaoyuan on 2018/11/7.
 *
 * 采集历史工单步骤
 */
public class HistoryOrders  extends WebDispatchSystem implements WebSystem{


    private WebDriver bro;
    private JavascriptExecutor broJs;

    private static final String CLICK = "arguments[0].click()";//单击操作 xpath
    private static final String REPORT_FROMS_XPATH = "//img[@alt = '报表平台']";
    private static final String USERS_REPORT_FROMS_XPATH = "//tr[@title = '用户层报表']/td[1]/img";
    private static final String USERS_REPORT_FROMS_DETAIL_XPATH = "//tr[@title = '用户层明细表']/td[1]/img";
    private static final String HISTORY_ORDERS = "//tr[@title = '03-省内装移机历史工单明细表']/td[3]/img";//历史工单明细表
    private static final String START_TIME_XPATH = "//input[@lable = '归档时间'][1]";
    private static final String END_TIME_XPATH = "//input[@lable = '归档时间'][3]";
    private static final String LOCAL_NETWORK = "//input[@lable = '本地网'][2]";
    private static final String LOCAL_NETWORK_L1_XPATH = "//tr[@title = '中国电信吉林公司']/td[@class = 'standartTreeImage'][1]/img";
    private static final String LOCAL_NETWORK_L2_XPATH = "//tr[@title = '中国电信吉林分公司']/td[@class = 'standartTreeImage'][2]/img";

    // private static final String GO_BACK_JS = "setTimeout(function(){document.getElementsByTagName('button')[4].click()},100)";
    //private static final String NO_CHANGE_PASS_XPATH = "//td[@align = 'left']/input[2]";

    public HistoryOrders(Browser browser){
        bro = browser.getBro();
        broJs = browser.getBroJs();
    }
   
    /**
     * 进入报表平台
     */
    public boolean gotoReportForms() {
        Log.info("进入报表平台");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("navigation");
            WebElement element = bro.findElement(By.xpath(REPORT_FROMS_XPATH));
            broJs.executeScript(CLICK, element);
            Log.sleep(3);
        } catch (Exception e) {
            Log.info(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 进入用户层报表
     *
     * @return boolean
     */
    public boolean gotoUserReportFroms() {
        Log.info("进入用户层报表");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("left");
            WebElement webElement = bro.findElement(By.xpath(USERS_REPORT_FROMS_XPATH));
            broJs.executeScript(CLICK, webElement);
            Log.sleep(1);
        } catch (Exception e) {
            Log.info(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 进入用户层明细表
     *
     * @return boolean
     */

     public boolean gotoUserReportFromsDetail() {

        Log.info("进入用户层明细表");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("left");
            WebElement webElement = bro.findElement(By.xpath(USERS_REPORT_FROMS_DETAIL_XPATH));
            broJs.executeScript(CLICK, webElement);
            Log.sleep(2);

        } catch (Exception e) {
            Log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;


    }


    /**
     * 进入省内装移机历史工单明细表
     *
     * @return boolean
     */

    public boolean gotoHisOrders() {
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
    public int selectOrders(String startTime, String endTime){
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
    public List<String> getOrders(int pageIndex) {
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


   /* public boolean goBack() {
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("worktable");
            bro.switchTo().frame("workarea");
            Log.sleep(3);
            Log.info("返回");
            broJs.executeScript(GO_BACK_JS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }*/

   


}
