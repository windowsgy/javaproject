package importData;


import base.TranslationUtils;
import base.Log;
import buildParams.Params;
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
        Client client = ES_Client.run(Params.es_cluster_name, Params.es_node1, Params.es_node2);
        Log.info("get index");
        List<String> listIndex = ES_Index.getIndex(client);
        if (!listIndex.contains(Params.es_index_name)) {//如果索引不存在
            Log.info("create index");
            if (!ES_Index.createIndex(Params.es_index_name, client)) {
                return ;
            }
            Log.info("build index mapping");
            ES_Mapping.buildIndexMap(Params.es_index_name, Params.es_index_type, client);
        }
        Log.info("bulk");
        ES_ImportData.bulk(client, listMap, Params.es_index_name, Params.es_index_type);
        client.close();

    }
}
