package userService;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

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
    private static final String BILL_MANG_XPATH = "//img[@alt = '工单管理']";
    private static final String INTEGRATED_QUERY_XPATH = "//tr[@title = '综合查询']/td[1]/img";
    private static final String NETWORK_BILL_SEARCH_XPATH = "//tr[@title = '网络层障碍工单查询']/td[3]/img";
    private static final String START_TIME_XPATH = "//input[@id = 'i_vBeginTime']";
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
     * 进入工单管理页面
     */
    public boolean gotoBillManage() {
        System.out.println("进入工单管理");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("navigation");
            WebElement element = bro.findElement(By.xpath(BILL_MANG_XPATH));
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
     * 进入综合查询页面
     *
     * @return boolean
     */
    public boolean gotoIntegratedQuery() {
        System.out.println("进入综合查询");
        try {
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("left");
            WebElement webElement = bro.findElement(By.xpath(INTEGRATED_QUERY_XPATH));
            broJs.executeScript(CLICK, webElement);
            sleep(2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean searchNetwokBill(String id) {
        try {
            System.out.println("网络层障碍工单查询 :" + id);
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("left");
            WebElement webElement = bro.findElement(By.xpath(NETWORK_BILL_SEARCH_XPATH));
            broJs.executeScript(CLICK, webElement);
            sleep(2);
            bro.switchTo().defaultContent();
            bro.switchTo().frame("mainFrame");
            bro.switchTo().frame("worktable");
            bro.switchTo().frame("workarea");
            webElement = bro.findElement(By.xpath(START_TIME_XPATH));//定位开始时间
            broJs.executeScript("arguments[0].removeAttribute('readOnly')", webElement);//修改时间属性为可变更
            webElement.clear();
            webElement.sendKeys(START_TIME);
            webElement = bro.findElement(By.xpath(TIME_LABEL_XPATH));//输入时间
            webElement.sendKeys(id);
            webElement = bro.findElement(By.xpath(SEARCH_BUTTON_XPATH));//查询按钮
            broJs.executeScript(CLICK, webElement);
            sleep(1);
            webElement = bro.findElement(By.xpath(JOIN_BILL_XPATH));//查看工单
            broJs.executeScript(CLICK, webElement);

        } catch (Exception e) {
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
