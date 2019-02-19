package base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 时间处理类 获取前一时间段字符格式 ，判断输入时间是否合规，
 * Created by jlgaoyuan on 2017/6/19.
 */
public class DateTimeUtils {

    /**
     * 获取当前 日期时间字段
     *
     * @return 日期字段
     */
    public  String getCurTime(String dateTimeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateTimeFormat);
        Calendar cal = Calendar.getInstance();
        return format.format(cal.getTime());
    }

    /**
     * 获取前n个分钟的 日期字段
     *
     * @return 日期字段
     */
    public  String getCurTimeMI() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        Calendar cal = Calendar.getInstance();// 取当前日期。
        return format.format(cal.getTime());
    }


    /**
     * 获取前n个分钟的 日期字段
     *
     * @return 日期字段
     */
    public  String getBeforeOneMI(int n) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        Calendar cal = Calendar.getInstance();// 取当前日期。
        cal.add(Calendar.MINUTE, -n);// 取当前时间的前n分钟.
        return format.format(cal.getTime());
    }

    /**
     * 获取前一个小时的 日期字段
     *
     * @return 日期字段
     */
    public  String getBeforeOneHour() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -1);// 取当前日期的前一小时.
        return format.format(cal.getTime());
    }

    /**
     * 获取前一个天的 日期字段
     *
     * @return 日期字段
     */
    public  String getBeforeOneDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal = Calendar.getInstance();// 取当前日期。
        cal.add(Calendar.DAY_OF_MONTH, -1);// 取当前日期的前一天.
        return format.format(cal.getTime());
    }

    /**
     * 分钟时间减 ，计算给定时间格式之前N分钟
     *
     * @param timeStr 时间格式字符串
     * @param n       时间差
     * @return 计算后时间
     */
    private  String computeTimeMi(String timeStr, int n) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = format.parse(timeStr);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -n);// 取指定时间的前n分钟.
        return format.format(calendar.getTime());
    }


    public  String computeTimeHH(String timeStr, int n) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = format.parse(timeStr);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -n);// 取指定时间的前n小时.
        return format.format(calendar.getTime());
    }

    /**
     * 计算起止时间差
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 时间差 单位秒
     */
    public  Long timeDifference(String startTime, String endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(endTime);
            Date d2 = df.parse(startTime);
            long diff = d1.getTime() - d2.getTime();
            long ss = diff ;
            return ss/1000;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 日期时间格式检查
     *
     * @param dateTime   日期时间格式
     * @param dateFormat 时间格式
     * @return boolean
     */
    public  boolean dtFormatCheak(String dateTime, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);// 设置日期转化成功标识
        try {
            format.parse(dateTime);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 时间戳格式字符串转换为 时间段格式 "yyyy-MM-dd HH:mm:ss"
     *
     * @param str 时间戳格式字段
     * @return 格式化日期时间
     */
    public  String timestampConvTime(String str) {
        String yyyy = str.substring(0, 4);
        String mm = str.substring(4, 6);
        String dd = str.substring(6, 8);
        String hh = str.substring(8, 10);
        String mi = str.substring(10, 12);
        String ss = str.substring(12, str.length());
        return yyyy + "-" + mm + "-" + dd + " " + hh + ":" + mi + ":" + ss;
    }

    /**
     * 时间段格式 "yyyy-MM-dd HH:mm:ss" 字符串转换为 时间戳格式
     *
     * @param str 时格式字段
     * @return 时间戳格式
     */
    public  String timeConvTimestamp(String str) {
        String yyyy = str.substring(0, 4);
        String mm = str.substring(5, 7);
        String dd = str.substring(8, 10);
        String hh = str.substring(11, 13);
        String mi = str.substring(14, 16);
        String ss = str.substring(17, 19);
        return yyyy + mm + dd + hh + mi + ss;
    }

    /**
     * 时间戳格式 只精确到分
     *
     * @param str 时格式字段
     * @return 时间戳格式
     */
    public  String timestampToMinute(String str) {
        return str.substring(0, str.length() - 2) + "00";
    }

    /**
     * 日期时间范围获 取时间戳List
     *
     * @param startTimeStr 开始时间
     * @param endTimeStr   结束时间
     * @return 返回时间戳List 单位 分钟
     */
    public  List<String> timeList(String startTimeStr, String endTimeStr) {
        List<String> timeList = new ArrayList<String>();
        Long timeDiffMi = timeDifference(startTimeStr, endTimeStr) / 60;//时间差 转换为秒
        int timeDiff = new Long(timeDiffMi).intValue();//Long To Int
        for (int i = 0; i < timeDiff; i++) {
            String timeStr =computeTimeMi(endTimeStr, i);
            timeList.add(timeStr);
        }
        return timeList;
    }

    /**
     * 时间戳格式字符串转换为 路径格式 "yyyy/MM/dd/HH/mm"
     *
     * @param str 时间戳格式字段
     * @return 路径格式
     */
    public  String timeFormatHDFSFilePathMI(String str) {
        String yyyy = str.substring(0, 4);
        String mm = str.substring(4, 6);
        String dd = str.substring(6, 8);
        String hh = str.substring(8, 10);
        String mi = str.substring(10, 12);
        String ss = "00";
        return yyyy + "/" + mm + "/" + dd + "/" + hh + "/" + mi;//HDFS子路径格式
    }

    /**
     * 时间戳格式字符串转换为 路径格式 "yyyy/MM/dd/HH/"
     *
     * @param str 时间戳格式字段
     * @return 路径格式
     */
    public  String timeFormatHDFSFilePathHH(String str) {
        String yyyy = str.substring(0, 4);
        String mm = str.substring(4, 6);
        String dd = str.substring(6, 8);
        String hh = str.substring(8, 10);
        String mi = str.substring(10, 12);
        String ss = "00";
        return yyyy + "/" + mm + "/" + dd + "/" + hh + "/";//HDFS子路径格式
    }


    /**
     * 日期时间格式检验
     * @param str 日期时间格式
     * @return boolean
     */
    public  boolean dateTimeFormat(String str){
        if("yyyy".equals(str)){ return true;}
        else if("yyyyMM".equals(str)){return true;}
        else if("yyyyMMdd".equals(str)){return true;}
        else if("yyyyMMddHH".equals(str)){return true;}
        else if("yyyyMMddHHmm".equals(str)){return true;}
        else if("yyyyMMddHHmmss".equals(str)){return true;}
        else if("yyyyMMddHHmmssSSS".equals(str)){return true;}
        else if("HH".equals(str)){return true;}
        else if("HHmm".equals(str)){return true;}
        else if("HHmmss".equals(str)){return true;}
        else if("HHmmssSSS".equals(str)){return true;}
        else if("yyyy-MM".equals(str)){return true;}
        else if("yyyy-MM-dd".equals(str)){return true;}
        else if("yyyy-MM-dd HH".equals(str)){return true;}
        else if("yyyy-MM-dd HH:mm".equals(str)){return true;}
        else if("yyyy-MM-dd HH:mm:ss".equals(str)){return true;}
        else if("HH:mm".equals(str)){return true;}
        else if("HH:mm:ss".equals(str)){return true;}
        else if("HH:mm:ss:SSS".equals(str)){return true;}
        else return false;
    }

    /**
     * 时间戳格式字符串转换为 ES 时间格式 "yyyy/MM/dd/HH/mm"
     * @param str 时间戳格式字段
     * @return string
     */
    public  String timeFormat2EStime(String str) {
        String yyyy = str.substring(0, 4);
        String mm = str.substring(4, 6);
        String dd = str.substring(6, 8);
        String hh = str.substring(8, 10);
        String mi = str.substring(10, 12);
        return yyyy + "-" + mm + "-" + dd + "T" + hh + ":" + mi + ":00.000Z";
    }
}