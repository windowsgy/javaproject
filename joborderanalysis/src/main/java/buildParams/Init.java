package buildParams;

import base.Log;

public class Init {
    /**
     * 参数初始化
     * @param args 参数
     * @return boolean
     *
     */
    public static boolean run(String [] args){
        Log.info("init");
        if(!ParamsCheck.run(args)){
            return false;
        }
        return LoadParams.run();
    }

}
