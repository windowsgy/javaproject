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
        if(!LoadProperties.paramMap("params.properties",Params.paramsMap)){
            return false;
        }
        Params.dataTimeFormat = Params.paramsMap.get("dataTimeFormat");
        Params.runTime = dateTimeUtils.getCurTime(Params.dataTimeFormat);

        Params.mainPath = Params.paramsMap.get("mainPath");
        Params.currentMainPath=Params.mainPath+Params.runTime;
        if(null == Params.sourcePath ){//如果程序启动未指定源数据路径，则设定源数据路径
            Params.sourcePath = Params.currentMainPath+Params.paramsMap.get("sourcePath");
        }

        Params.jsonPath = Params.currentMainPath+Params.paramsMap.get("jsonPath");

        Params.es_cluster_name = Params.paramsMap.get("es-cluster");
        Params.es_node1 = Params.paramsMap.get("es_node1");
        Params.es_node2 = Params.paramsMap.get("es_node2");

        Params.es_index_name = Params.paramsMap.get("es_index_name");
        Params.es_index_type = Params.paramsMap.get("es_index_type");



        if(null == Params.mainPath ||null == Params.currentMainPath
                ||null == Params.jsonPath ||null == Params.dataTimeFormat
                || null == Params.es_cluster_name || null == Params.es_node1
                ||null == Params.es_node2 ){
            return false;
        }
        return true;
    }
}
