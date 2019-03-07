package db.es;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class HistoryInstallOrders implements ES_Mapping {

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
                    .startObject("product").field("type", "keyword").endObject()
                    .startObject("includeOrderNum").field("type", "keyword").endObject()
                    .startObject("network").field("type", "keyword").endObject()
                    .startObject("orderNum").field("type", "keyword").endObject()
                    .startObject("accessNum").field("type", "keyword").endObject()
                    .startObject("speed").field("type", "keyword").endObject()
                    .startObject("salesman").field("type", "keyword").endObject()
                    .startObject("address").field("type", "keyword").endObject()
                    .startObject("standAdd").field("type", "keyword").endObject()
                    .startObject("area").field("type", "keyword").endObject()
                    .startObject("clientName").field("type", "keyword").endObject()
                    .startObject("linkMan").field("type", "keyword").endObject()
                    .startObject("linkPhoneNum").field("type", "keyword").endObject()
                    .startObject("commodityName").field("type", "keyword").endObject()
                    .startObject("executeType").field("type", "keyword").endObject()
                    .startObject("executeAction").field("type", "keyword").endObject()
                    .startObject("isPrint").field("type", "keyword").endObject()
                    .startObject("remark").field("type", "keyword").endObject()
                    .startObject("programCtl").field("type", "keyword").endObject()
                    .startObject("status").field("type", "keyword").endObject()
                    .startObject("priority").field("type", "keyword").endObject()
                    .startObject("executor").field("type", "keyword").endObject()
                    .startObject("executeDepartment ").field("type", "keyword").endObject()
                    .startObject("upTime").field("type", "keyword").endObject()
                    .startObject("feedbackTime").field("type", "keyword").endObject()
                    .startObject("reservationStartTime").field("type", "keyword").endObject()
                    .startObject("reservationEndTime").field("type", "keyword").endObject()
                    .startObject("reservationWarnTime").field("type", "keyword").endObject()
                    .startObject("taskEndTime").field("type", "keyword").endObject()
                    .startObject("acceptTime").field("type", "keyword").endObject()
                    .startObject("channelName").field("type", "keyword").endObject()
                    .startObject("arriveTime").field("type", "keyword").endObject()
                    .startObject("completedTime").field("type", "keyword").endObject()
                    .startObject("isOverTime").field("type", "keyword").endObject()
                    .startObject("lanPort").field("type", "keyword").endObject()
                    .startObject("eponPort").field("type", "keyword").endObject()
                    .startObject("onuPort").field("type", "keyword").endObject()
                    .startObject("odnPort").field("type", "keyword").endObject()
                    .startObject("odnName").field("type", "keyword").endObject()
                    .startObject("vindicatePattern").field("type", "keyword").endObject()
                    .startObject("partnerNumber").field("type", "keyword").endObject()
                    .startObject("partnerName").field("type", "keyword").endObject()
                    .startObject("partnerId").field("type", "keyword").endObject()
                    .startObject("causeUp").field("type", "keyword").endObject()
                    .startObject("causeFeedback").field("type", "keyword").endObject()
                    .startObject("fileType").field("type", "keyword").endObject()
                    .endObject().endObject();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return mapping;
    }
}
