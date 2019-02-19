package buildEnv;

import base.FileUtils;
import base.Log;
import buildParams.Params;

public class BuildEnv {

    public static boolean run(){
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
        Log.info("create json Path");
        if(!fileUtils.createDir(Params.jsonPath)){
            return false;
        }
        return true;
    }

}
