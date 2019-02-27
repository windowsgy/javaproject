package importData;


import base.TranslationUtils;
import base.Log;
import buildParams.Params;
import importData.esMapping.ES_Mapping;
import importData.esMapping.InstallOrdersMapping;
import org.elasticsearch.client.Client;

import java.util.List;
import java.util.Map;

public class ImportData {

    /**
     * 导入数据
     */
    public static void run(List<String> list) {
        TranslationUtils jsonUtils = new TranslationUtils();
        //json to map ;
        Log.info("json to map");
        List<Map<String, Object>> listMap = jsonUtils.jsonToMap(list);
        String importTime = listMap.get(0).get("importTime").toString();
        String importFile = listMap.get(0).get("importFile").toString();

        Log.info("build client");
        ES_Utils es_utils = new ES_Utils();
        Client client = es_utils.getClient(Params.es_cluster_name, Params.es_node1, Params.es_node2);
        Log.info("get index");
        List<String> listIndex = es_utils.getIndex(client);
        if (!listIndex.contains(Params.es_index_name)) {//如果索引不存在
            Log.info("create index");
            if (!es_utils.createIndex(Params.es_index_name, client)) {
                return ;
            }
            Log.info("build index mapping");
            ES_Mapping es_mapping = new InstallOrdersMapping();
            es_mapping.buildIndexMap(Params.es_index_name, Params.es_index_type, client);
        }
        Log.info("bulk");
        es_utils.bulk(client, listMap, Params.es_index_name, Params.es_index_type);
        client.close();

    }
}
