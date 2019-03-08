package web.get;

/**
 * WEB系统接口
 */
public interface WebSystem {
    boolean login(Browser browser , String url, String username, String password);
    void setUrl(String url);
    void setDriverPath(String driverPath);
    void setUsername(String username) ;
    void setPassword(String password) ;
    void setSavePath(String savePath);
    boolean getData(String startTime,String endTime);
}
