import base.FileUtils;

import buildEnv.BuildEnv;
import buildParams.ParamsBase;
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
        //ETL导入数据
        FileUtils fileUtils = new FileUtils();
        List<String> fileList = fileUtils.getFilesName(ParamsBase.sourcePath);
        for (String aFileList : fileList) {
            String sourcePath = ParamsBase.sourcePath + aFileList;
            String formatPath = ParamsBase.formatPath + aFileList;
            String jsonPath = ParamsBase.jsonPath + aFileList;
            List<String> listJson = Etl.run(sourcePath, formatPath,jsonPath);
         // ImportData.run(listJson);
        }
    }
}
