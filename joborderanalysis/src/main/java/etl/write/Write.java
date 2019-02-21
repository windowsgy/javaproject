package etl.write;

import base.FileUtils;

public class Write {
    public static boolean run(String path,String str){
        FileUtils fileUtils = new FileUtils();
        fileUtils.createFile(path);
        //写入文件
        return fileUtils.wrStr2File(str, path);

    }
}
