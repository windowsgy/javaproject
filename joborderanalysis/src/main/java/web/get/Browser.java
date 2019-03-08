package web.get;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * 浏览器驱动接口
 */
public interface Browser {
    WebDriver getBro();
    JavascriptExecutor getBroJs();
}
