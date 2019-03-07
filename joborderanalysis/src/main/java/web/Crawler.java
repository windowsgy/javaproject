package web;

import web.getWebData.GetHistoryInstallOrders;
import web.getWebData.GetWebData;
import init.Params;

public class Crawler {

    public static boolean run(){
        //运行爬虫采集
        GetWebData getWebData  = new GetHistoryInstallOrders(Params.url,Params.driverPath,Params.userName,Params.passWord,Params.webPath);
        String startTime = "2019-02-01";
        String endTime = "2019-02-01";
        ((GetHistoryInstallOrders) getWebData).getData(startTime,endTime);
        return true;
    }
}
