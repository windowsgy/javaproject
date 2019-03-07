package init;

import base.DateTimeUtils;

public class BuildParams {
    /**
     * 加载配置文件到map
     *
     * @return boolean
     */
    public static boolean run(String[] args) {
        Params.splitChar = "\\|\\|";
        Params.isolationChar = "||";
        DateTimeUtils dtUtils = new DateTimeUtils();
        //设定数据类型
        Params.dataType = args[0];
        //构建路径
        Params.mainPath = Params.paramsMap.get("mainPath");
        Params.currentMainPath = Params.mainPath + Params.runTime;

        if (Params.loadLocalData) {//程序运行为本地加载数据模式，则设定源数据路径用于爬虫采集
            Params.sourcePath = args[1];
        } else { //如果是设定时间运行
            Params.sourcePath = Params.currentMainPath + Params.paramsMap.get("sourcePath");
            if (Params.setTimeGet) {//如果是设定时间采集 第二个参数为设定的采集时间
                Params.startTime = args[1];
                Params.endTime = args[1];
            } else {//如果不是设定时间采集，当前时间的前一天为采集时间
                String afterOneDay = dtUtils.computeTimeHH(Params.runTime, 24, Params.dataTimeFormat);
                afterOneDay = afterOneDay.substring(0, 8);
                Params.startTime = afterOneDay;
                Params.endTime = afterOneDay;
            }
        }
        Params.jsonPath = Params.currentMainPath + Params.paramsMap.get("jsonPath");
        Params.formatPath = Params.currentMainPath + Params.paramsMap.get("formatPath");
        Params.webPath = Params.currentMainPath + Params.paramsMap.get("webPath");

        Params.es_cluster_name = Params.paramsMap.get("es-cluster");
        Params.es_node1 = Params.paramsMap.get("es_node1");
        Params.es_node2 = Params.paramsMap.get("es_node2");
        Params.es_index_name = Params.dataType.toLowerCase();
        Params.es_index_type = "data";

        Params.url = Params.paramsMap.get("url");
        Params.driverPath = Params.paramsMap.get("driverPath");
        Params.userName = Params.paramsMap.get("username");
        Params.passWord = Params.paramsMap.get("password");

        //设置类路径

        if (Params.dataType.equals(DataTypeEnum.HistoryInstallOrders.getValue()) && Params.loadLocalData) {
            Params.fileFormat = "Excel";
        } else {
            Params.fileFormat = "Html";
        }
        Params.addFieldsClassPath = "etl.addFields."+Params.dataType;
        Params.ordersClassPath = "etl.orders."+Params.dataType;
        Params.esMappingClassPath = "db.es."+Params.dataType;
        Params.webClassPath = "web.get."+Params.dataType;
        return true;
    }
}
