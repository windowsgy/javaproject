package buildParams;

import base.DateTimeUtils;
import base.FileUtils;
import base.Log;


public class ParamsCheck {

    /**
     * 对输入参数进行判断
     * 不输入参数为当前日期工单导出
     * 输入一个参数判断路径，日期，月份
     * 输入两个参数判断日期起止时间，月份起止时间
     */
    public static boolean run (String [] args){
        //参数检查
        if (args.length == 0) {//当前时间
            Log.info("current time ");
            return noParams();
        } else if (args.length == 1) { //输入日期获取工单 日/月
            return oneParams(args);
        } else if (args.length == 2) {  //输入时间范围获取工单 日/月
            return twoParams(args);
        } else {
            Log.error("input params error");
            return false;
        }
    }

    /**
     * 不输入参数使用当前系统时间作为参数
     * @return boolean
     */
    private static boolean noParams(){
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        Params.startTime = dateTimeUtils.getCurTime(Params.dataTimeFormat)+" 00:00:00";
        Params.endTime = Params.startTime+" 23:59:59";
        return true;
    }

    /**
     * 一个参数运行 指定日期或指定月份或指定数据所在本地路径
     * @param args 参数
     * @return boolean
     */
    private static boolean oneParams(String[] args){
        FileUtils fileUtils = new FileUtils();
        if (fileUtils.isDir(args[0])){
            Log.info("get local data path is : "+args[0]);
            Params.sourcePath = args[0];//设定本地数据路径
            Params.loadLocalData = true;//设定程序执行步骤为本地数据加载
        }else if (args[0].length()==6){//如果参数格式为月
            Log.info("get"+args[0]+" time orders");
        }else if (args[0].length()==8){//如果参数格式为日
            Log.info("get"+args[0]+" path data");
        }else{
            Log.error("input params error");
            return false;
        }
        return true;
    }

    /**
     * 连个参数运行 指定日期区间或指定月份区间
     * @param args 参数
     * @return boolean
     */
    private static boolean twoParams(String[] args){
        if(args[0].length() ==6 && args[1].length()==6){
            Log.info("get"+args[0]+ " to "+args[1] +" time orders");
        }else if (args[0].length() ==8 && args[1].length() ==8){
            Log.info("get"+args[0]+ " to "+args[1] +" time orders");
        }else{
            Log.error("input params error");
        }
        return false;
    }
}
