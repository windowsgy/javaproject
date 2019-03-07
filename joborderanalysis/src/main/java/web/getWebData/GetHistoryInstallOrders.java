package web.getWebData;

import base.FileUtils;
import base.Log;
import web.browsers.Browser;
import web.browsers.IeBrowser;

import web.webSystem.HistoryInstallOrders;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class GetHistoryInstallOrders implements GetWebData {

    private String url;
    private String driverPath;
    private String username;
    private String password;
    private String savePath;


    public GetHistoryInstallOrders(String url, String driverPath, String username, String password, String savePath){
        this.url = url;
        this.driverPath = driverPath;
        this.username = username;
        this.password = password;
        this.savePath = savePath;

    }

    /**
     * 爬取客调系统历史数据方法
     * @return boolean
     */
    public boolean  getData(String startTime,String endTime) {

        Browser browser = new IeBrowser(driverPath);//初始化浏览器

        WebDriver bro = browser.getBro();
        JavascriptExecutor broJs = browser.getBroJs();

        if(null == bro || null == broJs){
            return false;
        }

        HistoryInstallOrders webSystem = new HistoryInstallOrders(browser);

        //login
        if(!webSystem.login(browser,url,username,password)){
            return false;
        }

        //进入报表平台
        if (!webSystem.gotoReportForms()) {
            Log.error("进入报表平台失败");
            return false;
        }
        if (!webSystem.gotoUserReportFroms()) {
            Log.error("进入用户层报表失败");
            return false;
        }
        if (!webSystem.gotoUserReportFromsDetail()) {
            Log.error("进入用户层明细表失败");
            return false;
        }

        if(!webSystem.gotoHisOrders()){
            Log.error("省内装移机历史工单明细表失败");
        }

        //查询工单循环获取工单内容
        int pageIndex = webSystem.selectOrders(startTime,endTime);
        if (0 ==pageIndex ) {
            Log.error("查询历史工单明细表失败");
            return false;
        }
        else{
            Log.info("执行获取每页工单,按页保存");
            List<String>  list = webSystem.getOrders(pageIndex);
            Log.info("写入数据");
            FileUtils fileUtils = new FileUtils();
            String fileName = startTime+"_"+endTime;
            Log.info(fileName);
            for(int i = 0 ; i < list.size();i++){
                String filePath = savePath+fileName+"_"+i+".txt";
                fileUtils.createFile(filePath);
                fileUtils.wrStr2File(bro.getPageSource(),filePath);
            }
        }
        //关闭浏览器
        bro.close();
        return true;
    }
}
