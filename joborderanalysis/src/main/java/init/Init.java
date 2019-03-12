package init;

import base.LoadProperties;
import base.Log;

public class Init {

    public static boolean run(String [] args){
        Log.info("start init");
        Log.linel1();
        //加载配置文件
        Log.info("load properties");
        if(!LoadProperties.paramMap("params.properties", Params.paramsMap)){
            Log.error("load properties error");
            return false ;
        }
        Log.linel1();
        Log.info("init times params");
        if(!InitTimesParams.run()){
            Log.error("init times params error");
            return false;
        }
        //input params check
        Log.linel1();
        Log.info("input params check");
        if(!InputParamsCheck.run(args)){
            Log.error("input params error");
            return false;
        }
        Log.linel1();
        //构建参数
        Log.info("build params");
        if(!BuildParams.run(args)){
            Log.error("build params error");
            return false;
        }
        Log.linel1();
        //构建环境
        Log.info("build env");
        if(!BuildEnv.run()){
            Log.error("build env error");
            return false;
        }
        Log.linel1();
        Log.debug("runTime :"+Params.runTime);
        Log.debug("dataType :"+Params.dataType);
        Log.debug("fileFormat :"+Params.fileFormat);
        Log.info("init succeed");

        return true;
    }
}
