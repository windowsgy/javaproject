package web.webSystem;

import web.browsers.Browser;

public interface WebSystem {
    boolean login(Browser browser , String url, String username, String password);
}
