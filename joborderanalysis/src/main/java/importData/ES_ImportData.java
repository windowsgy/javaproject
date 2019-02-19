package importData;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.client.Client;

import java.util.List;
import java.util.Map;

public class ES_ImportData {

    public static void  bulk (Client client , List<Map<String,Object>> list, String indexName, String type) {
        try {
            int count = 0;
            // 开启批量插入
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            for (int i = 0; i < list.size(); i++) {
                count++;
                Map<String,Object> mapper = list.get(i);
                // log.info("JSON : "+value);
                bulkRequest.add(client.prepareIndex(indexName, type).setSource(mapper));
            }
            bulkRequest.execute().actionGet();
            bulkRequest.request().requests().clear();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
