package buildParams;

import java.util.HashMap;
import java.util.Map;

public class ParamsBase {

    static Map<String,String> paramsMap = new HashMap<>();

    //配置文件初始化参数
    public static String mainPath;//主路径
    public static String sourcePath;//源数据文件路径
    public static String formatPath;//格式化数据路径
    public static String jsonPath;//格式化文件路径
    public static String dataTimeFormat;//日期时间格式
    public static String currentMainPath;//程序当前运行源数据路径

    public static String es_cluster_name;
    public static String es_node1;//es node1
    public static String es_node2;//es node2
    public static String es_index_name;//es index name
    public static String es_index_type;//es index type


    //程序运行设定参数
    public static String runTime;//程序运行时间格式
    public static String startTime;//工单开始时间
    public static String endTime;//工单结束时间
    public static boolean loadLocalData = false;//是否加载本地数据



}