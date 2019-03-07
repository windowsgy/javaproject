package init;

import java.util.HashMap;
import java.util.Map;

public class Params {

    public static Map<String,String> paramsMap = new HashMap<>();

    //配置文件初始化参数
    static String mainPath;//主路径
    static String dataTimeFormat;//日期时间格式 秒
    static String dataTimeFormatDD;//日期时间格式 日
    public static String splitChar ;//切分字符
    public static String isolationChar;//分隔字符
    public static String currentMainPath;//程序当前运行源数据路径
    public static String sourcePath;//源数据文件路径
    public static String formatPath;//格式化数据路径
    public static String jsonPath;//格式化文件路径
    public static String webPath;//web下载数据路径


    //es 参数
    public static String es_cluster_name;
    public static String es_node1;//es node1
    public static String es_node2;//es node2
    public static String es_index_name;//es index name
    public static String es_index_type;//es index type


    //程序运行设定参数
    public static String runTime;//程序运行时间
    public static String dataType;//数据类型
    public static String fileFormat;//文件格式
    public static String startTime;//工单开始时间
    public static String endTime;//工单结束时间
    public static boolean loadLocalData = false;//是否加载本地数据
    public static boolean setTimeGet = false;//是否采集当前时间数据

    //爬虫数据

    public static String url;
    public static String driverPath;
    public static String userName;
    public static String passWord;
    public static String zone;
    public static final String LOGIN_XPATH = "//form[@name = 'loginform']/tbody/tr/td[3]/div";
    //类路径
    public static String ordersClassPath ;
    public static String addFieldsClassPath;
    public static String esMappingClassPath;
}
