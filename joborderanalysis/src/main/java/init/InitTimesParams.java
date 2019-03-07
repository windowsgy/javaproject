package init;

import base.DateTimeUtils;

public class InitTimesParams {

    public static boolean run(){
        DateTimeUtils dtUtils = new DateTimeUtils();
        Params.dataTimeFormat = Params.paramsMap.get("dataTimeFormat");
        Params.dataTimeFormatDD = Params.paramsMap.get("dataTimeFormatDD");
        Params.runTime = dtUtils.getCurTime(Params.dataTimeFormat);//初始化运行时间
        return null != Params.runTime && null != Params.dataTimeFormat && null != Params.dataTimeFormatDD;

    }
}
