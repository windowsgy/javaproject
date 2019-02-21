package buildParams;

import base.DateTimeUtils;
import base.LoadProperties;
import base.Log;

public class LoadParams {
    /**
     * 加载配置文件到map
     * @return boolean
     */
    public static boolean run(){
        Log.info("load properties");
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        if(!LoadProperties.paramMap("params.properties", ParamsBase.paramsMap)){
            return false;
        }
        ParamsBase.dataTimeFormat = ParamsBase.paramsMap.get("dataTimeFormat");
        ParamsBase.runTime = dateTimeUtils.getCurTime(ParamsBase.dataTimeFormat);

        ParamsBase.mainPath = ParamsBase.paramsMap.get("mainPath");
        ParamsBase.currentMainPath= ParamsBase.mainPath+ ParamsBase.runTime;
        if(null == ParamsBase.sourcePath ){//如果程序启动未指定源数据路径，则设定源数据路径
            ParamsBase.sourcePath = ParamsBase.currentMainPath+ ParamsBase.paramsMap.get("sourcePath");
        }

        ParamsBase.jsonPath = ParamsBase.currentMainPath+ ParamsBase.paramsMap.get("jsonPath");
        ParamsBase.formatPath = ParamsBase.currentMainPath+ ParamsBase.paramsMap.get("formatPath");

        ParamsBase.es_cluster_name = ParamsBase.paramsMap.get("es-cluster");
        ParamsBase.es_node1 = ParamsBase.paramsMap.get("es_node1");
        ParamsBase.es_node2 = ParamsBase.paramsMap.get("es_node2");

        ParamsBase.es_index_name = ParamsBase.paramsMap.get("es_index_name");
        ParamsBase.es_index_type = ParamsBase.paramsMap.get("es_index_type");


        return null != ParamsBase.mainPath && null != ParamsBase.currentMainPath
                && null != ParamsBase.jsonPath && null != ParamsBase.dataTimeFormat
                && null != ParamsBase.es_cluster_name && null != ParamsBase.es_node1
                && null != ParamsBase.es_node2;
    }
}
