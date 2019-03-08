package db.es;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class HistoryFailureOrders implements ES_Mapping {

    /**
     * 创建索引映射
     *
     * @param indexName indexName
     * @param typeName  type
     * @param client    es client
     */

    public void buildIndexMap(String indexName, String typeName, Client client) {

        PutMappingRequest mapping = Requests.putMappingRequest(indexName)
                .type(typeName).source(getAccessOrdersMapping());
        client.admin().indices().putMapping(mapping).actionGet();

    }

    /**
     * 构建接入型工单mapping
     *
     * @return builder
     */
    private  XContentBuilder getAccessOrdersMapping() {
        XContentBuilder mapping = null;
        try {
            mapping = jsonBuilder()
                    .startObject().startObject("properties")


                    .endObject().endObject();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return mapping;
    }
}
