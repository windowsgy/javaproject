package crawler;

import crawler.httpClient.Httptest;
import crawler.selenium.GetElement;

public class AutoWeb {

    public static boolean run(){

        GetElement.run();
       // Httptest.run();
        return true;

    }

}
