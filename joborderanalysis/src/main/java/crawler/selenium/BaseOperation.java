package crawler.selenium;

import buildParams.Params;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.ie.InternetExplorerDriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jlgaoyuan on 2018/11/7.
 *
 */
public class BaseOperation {

    public WebDriver getBro() {
        return bro;
    }

    public JavascriptExecutor getBroJs() {
        return broJs;
    }

    private WebDriver bro;
    private JavascriptExecutor broJs;
    private String url;
    private String driverPath;
    private String userName;
    private String passWord;
    private static final String CLICK = "arguments[0].click()";//单击操作 xpath
    private static final String REPORT_FROMS_XPATH = "//img[@alt = '报表平台']";
    private static final String USERS_REPORT_FROMS_XPATH = "//tr[@title = '用户层报表']/td[1]/img";
    private static final String USERS_REPORT_FROMS_DETAIL_XPATH = "//tr[@title = '用户层明细表']/td[1]/img";
    private static final String HISTORY_ORDERS ="//tr[@title = '03-省内装移机历史工单明细表']/td[3]/img";//历史工单明细表

    private static final String START_TIME_XPATH = "//input[@lable = '归档时间'][1]";
    private static final String END_TIME_XPATH = "//input[@lable = '归档时间'][3]";
    private static final String LOCAL_NETWORK  = "//input[@lable = '本地网']";
    private static final String SEARCH_BUTTON_XPATH = "//input[@name = 'searchbutton']";
    private static final String TIME_LABEL_XPATH = "//input[@id = 'mainSn']";
    private static final String JOIN_BILL_XPATH = "//a[@title = '点击进入查看']";
    private static final String START_TIME = "2018-01-01";
    private static final String GO_BACK_JS = "setTimeout(function(){document.getElementsByTagName('button')[4].click()},100)";
    //private static final String NO_CHANGE_PASS_XPATH = "//td[@align = 'left']/input[2]";

    public BaseOperation(String driverPath, String url, String username, String password) {
        this.url = url;
        this.driverPath = driverPath;
        this.userName = username;
        this.passWord = password;
    }

    /**
     * 初始化驱动
     *
     * @return boolean
     */
    public boolean ieInit() {
        //初始化驱动
        try {
            System.out.println(this.driverPath);
            System.setProperty("webdriver.ie.driver", this.driverPath);
            bro = new InternetExplorerDriver();
            broJs = ((JavascriptExecutor) bro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean login() {
        //设置隐性等待时间
        bro.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            bro.get(url);
            String loginUrl = bro.getCurrentUrl();
            System.out.println("login url :" + loginUrl);
            sleep(2);//等待2秒
            WebElement element = bro.findElement(By.name("userid"));//获取用户名元素
            element.sendKeys(this.userName);//发送用户名
            element = bro.findElement(By.name("passWord"));//获取密码元素
            //element.clear();
            element.clear();//清除密码
            element.sendKeys(this.passWord);//发送密码
            WebElement loginButton = bro.findElement(By.xpath(Params.LOGIN_XPATH));//获取登陆按钮元素
            broJs.executeScript(CLICK, loginButton);//点击登陆
            sleep(5);
            String afterLoginUrl = bro.getCurrentUrl();
            if (loginUrl.equals(afterLoginUrl)) {//如果登陆后页面等于登陆前页面，判断为登陆失败.
                return false;
            }
            System.out.println("login , currentPage :" + afterLoginUrl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }

    /**
     * 进入报表平台
     */
    public boolean gotoReportForms() {
        System.out.println("进入报表平台");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("navigation");
            WebElement element = bro.findElement(By.xpath(REPORT_FROMS_XPATH));
            broJs.executeScript(CLICK, element);
            sleep(2);
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
    public boolean gotoUserReportFroms() {
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
     * @return boolean
     */

    public boolean gotoUserReportFromsDetail(){

        System.out.println("进入用户层明细表");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("left");
            WebElement webElement = bro.findElement(By.xpath(USERS_REPORT_FROMS_DETAIL_XPATH));
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
     * 进入省内装移机历史工单明细表
     * @return
     */

    public boolean gotoHisOrders(String startTime,String endTime,String zone) {
        System.out.println("进入省内装移机历史工单明细表");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("left");
            WebElement webElement = bro.findElement(By.xpath(HISTORY_ORDERS));
            broJs.executeScript(CLICK, webElement);
            System.out.println(bro.getPageSource());
            sleep(2);
        /*    bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("worktable");*/

           // sleep(2);
            webElement = bro.findElement(By.xpath(START_TIME_XPATH));//定位开始时间
            broJs.executeScript("arguments[0].removeAttribute('readOnly')", webElement);//修改时间属性为可变更
            webElement.clear();
            webElement.sendKeys(startTime);

            webElement = bro.findElement(By.xpath(END_TIME_XPATH));//定位结束时间
            broJs.executeScript("arguments[0].removeAttribute('readOnly')", webElement);//修改时间属性为可变更
            webElement.clear();
            webElement.sendKeys(endTime);

            webElement = bro.findElement(By.xpath(LOCAL_NETWORK));//定位本地网
            System.out.println(webElement.toString());
            //修改时间属性为可变更
            //webElement.sendKeys(zone);
            //webElement.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }



    public boolean goBack() {
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

    }

    private void sleep(long timeLong) {
        timeLong = timeLong * 1000;
        try {
            Thread.sleep(timeLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}