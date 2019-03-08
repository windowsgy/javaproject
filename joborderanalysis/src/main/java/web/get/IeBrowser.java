package web.get;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


/**
 * IE浏览器接口
 */
public  class IeBrowser implements Browser {
    private WebDriver bro;
    private JavascriptExecutor broJs;

    public WebDriver getBro() {
        return bro;
    }

    public JavascriptExecutor getBroJs() {
        return broJs;
    }


    IeBrowser(String driverPath){
        try {
            System.setProperty("webdriver.ie.driver", driverPath);
            bro = new InternetExplorerDriver();
            broJs =  (JavascriptExecutor) bro;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
