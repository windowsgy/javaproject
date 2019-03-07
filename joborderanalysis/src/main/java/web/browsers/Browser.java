package web.browsers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public interface Browser {
    WebDriver getBro();
    JavascriptExecutor getBroJs();
}
