package buildEnv;

import base.FileUtils;
import base.Log;
import buildParams.Params;

public class BuildEnv {

    /**
     * 构建环境，创建目录
     * @return boolean
     */
    public static boolean run(){
        Log.info("Build Env");
        FileUtils fileUtils = new FileUtils();
        Log.info("create current main path");
        if(!fileUtils.createDir(Params.currentMainPath)){
            return false;
        }
        if(!Params.loadLocalData){
            Log.info("create sourcePath");
            if(!fileUtils.createDir(Params.sourcePath)){
                return false;
            }
        }
        Log.info("create format Path");
        fileUtils.createDir(Params.formatPath);
        Log.info("create json Path");
        fileUtils.createDir(Params.jsonPath);
        Log.info("create web Path");
        return fileUtils.createDir(Params.webPath);

    }

}
