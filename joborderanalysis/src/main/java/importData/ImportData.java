package importData;


import base.TranslationUtils;
import base.Log;
import buildParams.ParamsBase;
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
        List<Map<String, Object>> map = jsonUtils.jsonToMap(list);
        Log.info("build client");
        Client client = ES_Client.run(ParamsBase.es_cluster_name, ParamsBase.es_node1, ParamsBase.es_node2);
        Log.info("get index");
        List<String> listIndex = ES_Index.getIndex(client);
        if (!listIndex.contains(ParamsBase.es_index_name)) {//如果索引不存在
            Log.info("create index");
            if (!ES_Index.createIndex(ParamsBase.es_index_name, client)) {
                return ;
            }
            Log.info("build index mapping");
            ES_Mapping.buildIndexMap(ParamsBase.es_index_name, ParamsBase.es_index_type, client);
        }
        Log.info("bulk");
        ES_ImportData.bulk(client, map, ParamsBase.es_index_name, ParamsBase.es_index_type);
        client.close();

    }
}
