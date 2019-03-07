package web.browsers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public  class IeBrowser implements Browser {

    public WebDriver getBro() {
        return bro;
    }

    public JavascriptExecutor getBroJs() {
        return broJs;
    }

    private WebDriver bro;
    private JavascriptExecutor broJs;

    public IeBrowser(String driverPath){
        try {
            System.setProperty("webdriver.ie.driver", driverPath);
            bro = new InternetExplorerDriver();
            broJs =  (JavascriptExecutor) bro;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
