package init;

import base.DateTimeUtils;
import base.FileUtils;
import base.Log;


/**
 * 输入参数检查
 */
public class InputCheck {

    public static boolean run(String[] args) {

        if (1 == args.length) {//单参数判断
            if (!DataTypeEnum.in(args[0])) {
                Log.error("输入数据类型错误");
                return false;
            }
        } else if (2 == args.length) {//两个参数判断
            if (!DataTypeEnum.in(args[0])) {
                Log.error("输入数据类型错误");
                return false;
            }
            if (checkInputPath(args[1])) { //输入路径判断
                Params.loadLocalData = true;//设定运行模式为本地数据加载模式
            } else if (checkInputTime(args[1])) {  //输入时间判断
                Params.setTimeGet = true;//设定时间采集为true
            } else {
                return false;
            }
        } else {
            Log.error("输入参数数量错误");
            return false;

        }
        return true;
    }


    /**
     * 输入时间参数判断
     *
     * @param timesStr 时间参数
     * @return boolean
     */
    private static boolean checkInputTime(String timesStr) {
        DateTimeUtils dtUtils = new DateTimeUtils();
        if (!dtUtils.dtCheck(timesStr, Params.dataTimeFormatDD)) {
            Log.error("输入时间参数错误");
            return false;
        }
        String afterOneDay = dtUtils.computeTimeHH(Params.runTime, 24, Params.dataTimeFormat);
        afterOneDay = afterOneDay.substring(0, 8);
        //计算时间差 当前运行时间 - 输入时间
        Long timeLong = dtUtils.timeDifference(timesStr, afterOneDay, Params.dataTimeFormatDD);
        //如果 当前运行时间 小于 输入时间 返回
        if (timeLong < 0) {
            Log.error("输入时间大于昨日");
            return false;
        }
        return true;
    }

    /**
     * 输入路径参数判断
     *
     * @param pathStr 路径参数
     * @return boolean
     */
    private static boolean checkInputPath(String pathStr) {
        FileUtils fileUtils = new FileUtils();
        if (fileUtils.isDir(pathStr)) {//如果目录存在
            if (fileUtils.getFilesName(pathStr).size() < 1) {//如果路径下有文件
                Log.error("指定路径下无文件");
                return false;
            }
        } else {
            Log.error("指定路径不存在");
            return false;
        }
        return true;
    }
}
