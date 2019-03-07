package web.get;

import base.Log;
import init.Params;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.browsers.Browser;


/**
 * 客调系统抽象方法
 */
public abstract class DispatchSystem implements WebSystem {

    private static final String CLICK = "arguments[0].click()";//单击操作 xpath
    private static final String REPORT_FROMS_XPATH = "//img[@alt = '报表平台']";
    private static final String USERS_REPORT_FROMS_XPATH = "//tr[@title = '用户层报表']/td[1]/img";
    private static final String USERS_REPORT_FROMS_DETAIL_XPATH = "//tr[@title = '用户层明细表']/td[1]/img";

    // private static final String GO_BACK_JS = "setTimeout(function(){document.getElementsByTagName('button')[4].click()},100)";
    //private static final String NO_CHANGE_PASS_XPATH = "//td[@align = 'left']/input[2]";


    /**
     * 进入报表平台
     */
    boolean gotoReportForms(WebDriver bro, JavascriptExecutor broJs) {
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
    boolean gotoUserReportFroms(WebDriver bro, JavascriptExecutor broJs) {
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

    boolean gotoUserReportFromsDetail(WebDriver bro, JavascriptExecutor broJs) {

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

    /**
     * 登陆客调系统
     *
     * @return boolean
     */
    @Override
    public boolean login(Browser browser, String url, String username, String password) {
        String CLICK = "arguments[0].click()";//单击操作 xpath
        WebDriver bro = browser.getBro();
        JavascriptExecutor broJs = browser.getBroJs();
        //设置隐性等待时间
        //bro.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            bro.get(url);
            String loginUrl = bro.getCurrentUrl();
            Log.info("login url :" + loginUrl);
            Log.sleep(3);//等待2秒
            WebElement element = bro.findElement(By.name("userid"));//获取用户名元素
            element.sendKeys(username);//发送用户名
            element = bro.findElement(By.name("passWord"));//获取密码元素
            //element.clear();
            element.clear();//清除密码
            element.sendKeys(password);//发送密码
            WebElement loginButton = bro.findElement(By.xpath(Params.LOGIN_XPATH));//获取登陆按钮元素
            broJs.executeScript(CLICK, loginButton);//点击登陆
            Log.sleep(3);
            String afterLoginUrl = bro.getCurrentUrl();
            if (loginUrl.equals(afterLoginUrl)) {//如果登陆后页面等于登陆前页面，判断为登陆失败.
                return false;
            }
            Log.info("login , currentPage :" + afterLoginUrl);
        } catch (Exception e) {
            Log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;

    }

}
