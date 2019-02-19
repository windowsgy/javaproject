import buildEnv.BuildEnv;
import format.Format;
import buildParams.Init;
import importData.ImportData;


public class Start {

    public static void run(String[] args) {



        //参数初始化
        if(!Init.run(args)){//如果加载失败返回
            return ;
        }

        //创建环境

        if(!BuildEnv.run()){
            return;
        }

        //运行

/*
        if(!Params.loadLocalData){//如果不是加载本地数据则进行数据爬取模块
            Crawler.run();

        }*/

        //数据格式化模块执行

        if(!Format.accessOrders()){
            return;
        }

        ImportData.run();

        //数据导入执行



    }




}
