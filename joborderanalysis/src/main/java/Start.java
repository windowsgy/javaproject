import buildEnv.BuildEnv;
import buildParams.Init;
import buildParams.Params;
import crawler.getData.GetHistoryOrders;
import crawler.getData.GetWebData;


public class Start {

    /**
     *
     * @param args 输入参数
     */
    public static void run(String[] args) {
        //参数初始化
        if (!Init.run(args)) {//如果加载失败返回
            return;
        }
        //创建环境
        if (!BuildEnv.run()) {
            return;
        }
        //运行爬虫采集
        GetWebData getWebData  = new GetHistoryOrders(Params.url,Params.driverPath,Params.userName,Params.passWord,Params.webPath);
        String startTime = "2019-02-01";
        String endTime = "2019-02-01";
        ((GetHistoryOrders) getWebData).getData(startTime,endTime);

         /*
        if(!Params.loadLocalData){//如果不是加载本地数据则进行数据爬取模块
            AutoWeb.run();

        }
       //ETL导入数据
        FileUtils fileUtils = new FileUtils();
        List<String> fileList = fileUtils.getFilesName(Params.sourcePath);
        for (String aFileList : fileList) {
            String sourcePath = Params.sourcePath + aFileList;
            String formatPath = Params.formatPath + aFileList;
            String jsonPath = Params.jsonPath + aFileList;
            List<String> listJson = Etl.run(sourcePath, formatPath,jsonPath);
         // ImportData.run(listJson);
        }*/
    }
}
