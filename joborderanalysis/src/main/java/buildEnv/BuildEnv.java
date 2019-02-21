package buildEnv;

import base.FileUtils;
import base.Log;
import buildParams.ParamsBase;

public class BuildEnv {

    /**
     * 构建环境，创建目录
     * @return boolean
     */
    public static boolean run(){
        FileUtils fileUtils = new FileUtils();
        Log.info("create current main path");
        if(!fileUtils.createDir(ParamsBase.currentMainPath)){
            return false;
        }
        if(!ParamsBase.loadLocalData){
            Log.info("create sourcePath");
            if(!fileUtils.createDir(ParamsBase.sourcePath)){
                return false;
            }
        }
        Log.info("create format Path");
        fileUtils.createDir(ParamsBase.formatPath);
        Log.info("create json Path");
        return fileUtils.createDir(ParamsBase.jsonPath);
    }

}
