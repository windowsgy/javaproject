package crawler;

import base.FileUtils;
import base.Log;
import buildParams.Params;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.ie.InternetExplorerDriver;


import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jlgaoyuan on 2018/11/7.
 *
 * 采集历史工单步骤
 */
class HistoryOrdersStep {


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

    /**
     * 初始化驱动
     *
     * @return boolean
     */
    boolean ieInit(String driverPath) {
        //初始化驱动
        try {
            System.out.println(driverPath);
            System.setProperty("webdriver.ie.driver", driverPath);
            bro = new InternetExplorerDriver();
            broJs = ((JavascriptExecutor) bro);
            sleep(5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 登陆
     * @return boolean
     */

    boolean login(String url, String username, String password) {
        //设置隐性等待时间
        bro.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            bro.get(url);
            String loginUrl = bro.getCurrentUrl();
            Log.info("login url :" + loginUrl);
            sleep(3);//等待2秒
            WebElement element = bro.findElement(By.name("userid"));//获取用户名元素
            element.sendKeys(username);//发送用户名
            element = bro.findElement(By.name("passWord"));//获取密码元素
            //element.clear();
            element.clear();//清除密码
            element.sendKeys(password);//发送密码
            WebElement loginButton = bro.findElement(By.xpath(Params.LOGIN_XPATH));//获取登陆按钮元素
            broJs.executeScript(CLICK, loginButton);//点击登陆
            sleep(3);
            String afterLoginUrl = bro.getCurrentUrl();
            if (loginUrl.equals(afterLoginUrl)) {//如果登陆后页面等于登陆前页面，判断为登陆失败.
                return false;
            }
            Log.info("login , currentPage :" + afterLoginUrl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }

    /**
     * 进入报表平台
     */
    boolean gotoReportForms() {
        System.out.println("进入报表平台");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("navigation");
            WebElement element = bro.findElement(By.xpath(REPORT_FROMS_XPATH));
            broJs.executeScript(CLICK, element);
            sleep(3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    boolean gotoUserReportFroms() {
        System.out.println("进入用户层报表");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("left");
            WebElement webElement = bro.findElement(By.xpath(USERS_REPORT_FROMS_XPATH));
            broJs.executeScript(CLICK, webElement);
            sleep(2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    boolean gotoUserReportFromsDetail() {

        System.out.println("进入用户层明细表");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("left");
            WebElement webElement = bro.findElement(By.xpath(USERS_REPORT_FROMS_DETAIL_XPATH));
            broJs.executeScript(CLICK, webElement);
            sleep(2);

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

    int gotoHisOrders(String startTime, String endTime) {
        Log.info("进入省内装移机历史工单明细表");
        int pageIndex = 0 ;
        try {
            WebElement webElement = bro.findElement(By.xpath(HISTORY_ORDERS));
            broJs.executeScript(CLICK, webElement);
            sleep(3);
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("worktable");
            bro.switchTo().frame("workarea");
            sleep(3);
            webElement = bro.findElement(By.xpath(START_TIME_XPATH));//定位开始时间
            broJs.executeScript("arguments[0].removeAttribute('readOnly')", webElement);//修改时间属性为可变更
            webElement.clear();
            webElement.sendKeys(startTime);
            webElement = bro.findElement(By.xpath(END_TIME_XPATH));//定位结束时间
            broJs.executeScript("arguments[0].removeAttribute('readOnly')", webElement);//修改时间属性为可变更
            webElement.clear();
            webElement.sendKeys(endTime);
            sleep(3);
            webElement = bro.findElement(By.xpath(LOCAL_NETWORK));//定位本地网
            broJs.executeScript(CLICK, webElement);
            sleep(3);
            webElement = bro.findElement(By.xpath(LOCAL_NETWORK_L1_XPATH));//本地网选择
            broJs.executeScript(CLICK, webElement);
            sleep(5);
            webElement = bro.findElement(By.xpath(LOCAL_NETWORK_L2_XPATH));//本地网选择
            broJs.executeScript(CLICK, webElement);
            sleep(5);
            //获取所有按钮
            List<WebElement> buttonList = bro.findElements(By.tagName("BUTTON"));
            //本地网确定按钮
            webElement = buttonList.get(11);
            broJs.executeScript(CLICK, webElement);
            sleep(5);
            //生成报表按钮
            webElement = buttonList.get(0);
            broJs.executeScript(CLICK, webElement);
            sleep(10);
            //获取页面数量
            String pageHtml = bro.getPageSource();
            String pageNumber = pageHtml.substring(pageHtml.indexOf("共计") + 2, pageHtml.indexOf("页;转到第"));
            pageIndex = Integer.valueOf(pageNumber);
            return pageIndex;

        } catch (Exception e) {
            Log.error(e.getMessage());
            e.printStackTrace();
        }
        return pageIndex;
    }

    /**
     * 进入每个页面下载文件
     *
     * @param pageIndex 页索引
     */
    void getOrders(int pageIndex, String savePath, String fileName) {
        FileUtils fileUtils = new FileUtils();
        for (int i = 1; i <= pageIndex; i++) {
            WebElement webElement = bro.findElement(By.name("skippage"));
            webElement.clear();
            webElement.sendKeys(String.valueOf(i));
            sleep(5);
            webElement = bro.findElement(By.className("button"));
            broJs.executeScript(CLICK, webElement);
            sleep(10);
            String filePath = savePath+fileName+"_"+i+".txt";
            fileUtils.createFile(filePath);
            fileUtils.wrStr2File(bro.getPageSource(),filePath);

        }
    }


   /* public boolean goBack() {
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("worktable");
            bro.switchTo().frame("workarea");
            sleep(3);
            System.out.println("返回");
            broJs.executeScript(GO_BACK_JS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }*/

    private void sleep(long timeLong) {
        timeLong = timeLong * 1000;
        try {
            Thread.sleep(timeLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
