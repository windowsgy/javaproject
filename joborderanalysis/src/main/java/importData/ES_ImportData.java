package importData;

import base.Log;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.Client;

import java.util.List;
import java.util.Map;

class ES_ImportData {

    static void  bulk(Client client, List<Map<String, Object>> list, String indexName, String type) {
        try {
            Log.info("data size is :"+list.size());
            int count = 0;
            // 开启批量插入
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            for (Map<String, Object> maps : list) {
                count++;
                // log.info("JSON : "+value);
                bulkRequest.add(client.prepareIndex(indexName, type).setSource(maps));
            }
            bulkRequest.execute().actionGet();
            bulkRequest.request().requests().clear();
            Log.info("bulk to es data size is :"+count);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
