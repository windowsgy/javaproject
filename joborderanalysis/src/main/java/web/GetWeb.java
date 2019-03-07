package web;

import init.Params;
import web.get.WebSystem;

public class GetWeb {

    public static boolean run(){
        //运行爬虫采集
        try {
        WebSystem  getData = (WebSystem) Class.forName(Params.webClassPath).newInstance();
        getData.setUrl(Params.url);
        getData.setDriverPath(Params.driverPath);
        getData.setUsername(Params.userName);
        getData.setPassword(Params.passWord);
        getData.setSavePath(Params.webPath);
        getData.getData(Params.startTime,Params.endTime);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
