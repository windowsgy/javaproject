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
                    .startObject("orderNum").field("type", "keyword").endObject()
                    .startObject("status").field("type", "keyword").endObject()
                    .startObject("acceptTime").field("type", "keyword").endObject()
                    .startObject("network").field("type", "keyword").endObject()
                    .startObject("businessNum").field("type", "keyword").endObject()
                    .startObject("businessType").field("type", "keyword").endObject()
                    .startObject("userName").field("type", "keyword").endObject()
                    .startObject("userLevel").field("type", "keyword").endObject()
                    .startObject("address").field("type", "keyword").endObject()
                    .startObject("declarePheno").field("type", "keyword").endObject()
                    .startObject("declarePhenoDesc").field("type", "keyword").endObject()
                    .startObject("ordersSource").field("type", "keyword").endObject()
                    .startObject("linkMan").field("type", "keyword").endObject()
                    .startObject("linkPhoneNum").field("type", "keyword").endObject()
                    .startObject("firstResponseTime").field("type", "keyword").endObject()
                    .startObject("firstResponseOverTime").field("type", "keyword").endObject()
                    .startObject("receiptTime").field("type", "keyword").endObject()
                    .startObject("receiptCause").field("type", "keyword").endObject()
                    .startObject("principal").field("type", "keyword").endObject()
                    .startObject("principalPost").field("type", "keyword").endObject()
                    .startObject("filedTime").field("type", "keyword").endObject()
                    .startObject("filedMan").field("type", "keyword").endObject()
                    .startObject("preprocessor").field("type", "keyword").endObject()
                    .startObject("preprocessorContent").field("type", "keyword").endObject()
                    .startObject("replier").field("type", "keyword").endObject()
                    .startObject("replyContent").field("type", "keyword").endObject()
                    .startObject("addInfoContent").field("type", "keyword").endObject()
                    .startObject("rebuildContent").field("type", "keyword").endObject()
                    .startObject("overTime").field("type", "keyword").endObject()
                    .startObject("clientBase").field("type", "keyword").endObject()
                    .startObject("warn").field("type", "keyword").endObject()
                    .startObject("controlOverTime").field("type", "keyword").endObject()
                    .startObject("urgeRepair").field("type", "keyword").endObject()
                    .startObject("feedbackContent").field("type", "keyword").endObject()
                    .startObject("urgeRepairContent").field("type", "keyword").endObject()
                    .startObject("autoReturnVisitResult").field("type", "keyword").endObject()
                    .startObject("autoReturnVisitStatus").field("type", "keyword").endObject()
                    .startObject("customerSatisfaction").field("type", "keyword").endObject()
                    .startObject("speed").field("type", "keyword").endObject()
                    .startObject("accessWay").field("type", "keyword").endObject()
                    .startObject("theDay").field("type", "keyword").endObject()
                    .startObject("theDayAgreement").field("type", "keyword").endObject()
                    .endObject().endObject();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return mapping;
    }
}
