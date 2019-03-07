package etl.utils;

import base.StringUtils;

public class SetFields {
    public static boolean yesOrNo(String string){
        return !string.contains("否");
    }

    public static double toDouble(String string){
        StringUtils stringUtils = new StringUtils();
        if(stringUtils.isNumeric(string)){
            return Double.parseDouble(string);
        }else return 0.0;

    }
}
