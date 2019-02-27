package crawler.webSystem;

import base.Log;
import buildParams.Params;
import crawler.browsers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * 客调系统抽象方法
 */
public abstract class WebDispatchSystem implements WebSystem {

    /**
     * 登陆客调系统
     * @return boolean
     */
    public boolean login(Browser browser , String url, String username, String password) {
        String CLICK = "arguments[0].click()";//单击操作 xpath
        WebDriver  bro = browser.getBro();
        JavascriptExecutor broJs = browser.getBroJs();
        //设置隐性等待时间
        bro.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
