package base;

/**
 * 日志记录类
 * Created by Telis on 17/7/12.
 * info 打印输出 并可选记录文件
 * debug 打印输出 并可选记录文件
 * warn 打印输出 并可选记录文件
 * error 打印输出 并可选记录文件
 * msg 消息不记录文件
 * out 系统输出不换行，不记录文件
 */
public class Log {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static DateTimeUtils dtUtils = new DateTimeUtils();
    private static boolean debugOnOff = false;//debug 开关
    private static boolean outOnOff = true;
    private static FileUtils fileUtils = new FileUtils();


    private static String logFilePath = null;
    public static void setDebug(boolean debug) {
        debugOnOff = debug;
    }
    public static void setOut(boolean out) {
        outOnOff = out;
    }



    public static<T> void debug(T x) {
        if (debugOnOff) {
            String message = "\033[35;4m" + "[debug][" + dtUtils.getCurTime(DATE_FORMAT) + "]" + x + "\033[0m" + "\r\n";
            System.out.print(message);
            if (logFilePath != null) {
                fileUtils.wrStr2File(message, logFilePath, "utf-8");
            }
        }
    }


    public static<T> void info(T x) {
        String message = "[info][" + dtUtils.getCurTime(DATE_FORMAT) + "]" + x + "\r\n";
        System.out.print(message);
        if (logFilePath != null) {
            fileUtils.wrStr2File(message, logFilePath, "utf-8");
        }
    }


    public static <T> void warn(T x) {
        String message = "\033[35;4m"+"[warn][" + dtUtils.getCurTime(DATE_FORMAT) + "]" + x + "\033[0m" +  "\r\n";
        System.out.print(message);
        if (logFilePath != null) {
            fileUtils.wrStr2File(message, logFilePath, "utf-8");
        }
    }

    public static<T> void error(T x) {
        String message = "[error][" + dtUtils.getCurTime(DATE_FORMAT) + "]" + x + "\r\n";
        System.err.print(message);
        if (logFilePath != null) {
            fileUtils.wrStr2File(message, logFilePath, "utf-8");
        }
    }

    public static<T> void out(T x) {
        if (outOnOff) {
            System.out.print(x);
        }
    }

    public static <T> void msg(T x) {
        if (outOnOff) {
            System.out.print(x);
        }
    }

    public static void linel0() {
        String message = "#############################################################################################" + "\r\n";
        System.out.print(message);
        if (logFilePath != null) {
            fileUtils.wrStr2File(message, logFilePath, "utf-8");
        }
    }

    public static void linel1() {
        String message = "=============================================================================" + "\r\n";
        System.out.print(message);
        if (logFilePath != null) {
            fileUtils.wrStr2File(message, logFilePath, "utf-8");
        }
    }

    public static void linel2() {
        String message = "**************************************************************" + "\r\n";
        System.out.print(message);
        if (logFilePath != null) {
            fileUtils.wrStr2File(message, logFilePath, "utf-8");
        }
    }


    public static void linel3() {
        String message = "---------------------------------------" + "\r\n";
        System.out.print(message);
        if (logFilePath != null) {
            fileUtils.wrStr2File(message, logFilePath, "utf-8");
        }
    }

    public static void linel4() {
        String message = "............................." + "\r\n";
        System.out.print(message);
        if (logFilePath != null) {
            fileUtils.wrStr2File(message, logFilePath, "utf-8");
        }
    }

    public static void  sleep(long timeLong) {
        timeLong = timeLong * 1000;
        try {
            Thread.sleep(timeLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
