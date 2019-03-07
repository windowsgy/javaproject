package db;


import base.TranslationUtils;
import base.Log;
import init.Params;
import db.es.ES_Mapping;
import org.elasticsearch.client.Client;

import java.util.List;
import java.util.Map;

public class ImportData {

    /**
     * 导入数据
     */
    public static void run(Map<String, List<String>> map) {
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            TranslationUtils jsonUtils = new TranslationUtils();
            //json to map ;
            Log.info("json to map");
            List<Map<String, Object>> listMap = jsonUtils.jsonToMap(list);
            Log.info("build client");
            ES_Utils es_utils = new ES_Utils();
            Client client = es_utils.getClient(Params.es_cluster_name, Params.es_node1, Params.es_node2);
            Log.info("get index");
            List<String> listIndex = es_utils.getIndex(client);
            if (!listIndex.contains(Params.es_index_name)) {//如果索引不存在
                Log.info("create index");
                if (!es_utils.createIndex(Params.es_index_name, client)) {
                    return;
                }
                Log.info("build index mapping");
                try {
                    ES_Mapping es_mapping = (ES_Mapping) Class.forName(Params.ordersClassPath).newInstance();
                    es_mapping.buildIndexMap(Params.es_index_name, Params.es_index_type, client);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            Log.info("bulk");
            es_utils.bulk(client, listMap, Params.es_index_name, Params.es_index_type);
            client.close();

        }
    }
}
