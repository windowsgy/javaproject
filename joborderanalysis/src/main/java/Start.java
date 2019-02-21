import base.FileUtils;

import buildEnv.BuildEnv;
import buildParams.Params;
import buildParams.Init;
import etl.Etl;
import importData.ImportData;

import java.util.List;


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
        //运行
        /*
        if(!Params.loadLocalData){//如果不是加载本地数据则进行数据爬取模块
            Crawler.run();

        }*/
        //ETL
        FileUtils fileUtils = new FileUtils();
        List<String> fileList = fileUtils.getFilesName(Params.sourcePath);
        for (String aFileList : fileList) {
            String sourcePath = Params.sourcePath + aFileList;
            String desPath = Params.jsonPath + aFileList;
            Etl.run(sourcePath, desPath);
        }
        ImportData.run();
    }
}
