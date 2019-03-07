package db.es;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class InstallOrders implements ES_Mapping {

    /**
     * 创建索引映射
     * @param indexName indexName
     * @param typeName  type
     * @param client    es client
     */

    public  void buildIndexMap(String indexName, String typeName, Client client) {

        PutMappingRequest mapping = Requests.putMappingRequest(indexName)
                .type(typeName).source(getAccessOrdersMapping());
        client.admin().indices().putMapping(mapping).actionGet();

    }

    /**
     * 构建接入型工单mapping
     * @return builder
     */
    private  XContentBuilder getAccessOrdersMapping() {
        XContentBuilder mapping = null;
        try {
            mapping = jsonBuilder()
                    .startObject().startObject("properties")
                    // 客户经理 salesman;
                    .startObject("salesman").field("type", "keyword").endObject()
                    // 当当快 efficient;
                    .startObject("efficient").field("type", "keyword").endObject()
                    // 小区名称 residenceCommunity;
                    .startObject("residenceCommunity").field("type", "keyword").endObject()
                    //装机地址 address;
                    .startObject("address").field("type", "keyword").endObject()
                    //接入号 accessNum;
                    .startObject("accessNum").field("type", "keyword").endObject()
                    //客户名称 clientName;
                    .startObject("clientName").field("type", "keyword").endObject()
                    //状态 status;
                    .startObject("status").field("type", "keyword").endObject()
                    //预约上门时间 makeServiceTime;
                    .startObject("makeServiceTime").field("type", "keyword").endObject()
                    //任务结束时间 taskEndTime;
                    .startObject("taskEndTime").field("type", "keyword").endObject()
                    //受理时间 acceptTime;
                    .startObject("acceptTime").field("type", "keyword").endObject()
                    //联系人 linkMan;
                    .startObject("lineMan").field("type", "keyword").endObject()
                    //联系电话 linkPhoneNum;
                    .startObject("linkPhoneNum").field("type", "keyword").endObject()
                    //产品 product;
                    .startObject("product").field("type", "keyword").endObject()
                    //业务类型 businessType;
                    .startObject("businessType").field("type", "keyword").endObject()
                    //商品名称 commodityName;
                    .startObject("commodityName").field("type", "keyword").endObject()
                    //标准地址 standAdd;
                    .startObject("standAdd").field("type", "keyword").endObject()
                    //楼道交换机端口 lanPort;
                    .startObject("lanPort").field("type", "keyword").endObject()
                    // EPON端口 eponPort;
                    .startObject("eponPort").field("type", "keyword").endObject()
                    //ONU设备名称及端口 onuPort;
                    .startObject("onuPort").field("type", "keyword").endObject()
                    //分光器端口 odnPort;
                    .startObject("odnPort").field("type", "keyword").endObject()
                    //分光器名称 odnName;
                    .startObject("odnName").field("type", "keyword").endObject()
                    //退单原因 returnReason;
                    .startObject("returnReason").field("type", "keyword").endObject()
                    //施工岗(人)/部门 executor;
                    .startObject("executor").field("type", "keyword").endObject()
                    //最后回单操作人 finalOperator;
                    .startObject("finalOperator").field("type", "keyword").endObject()
                    //客户分群      clientGroup;
                    .startObject("clientGroup").field("type", "keyword").endObject()
                    //备注      remarks;
                    .startObject("remarks").field("type", "keyword").endObject()
                    //渠道名称      channelName;
                    .startObject("channelName").field("type", "keyword") .endObject()
                    //10000号受理渠道标志      tabFor10000;
                    .startObject("tabFor10000").field("type", "keyword").endObject()
                    //原安装地址      installAddSource;
                    .startObject("installAddSource").field("type", "keyword").endObject()
                    //原标准地址      standAddSource;
                    .startObject("standAddSource")
                    .field("type", "keyword")
                    .endObject()
                    //带宽      bandwidth;
                    .startObject("bandwidth")
                    .field("type", "keyword")
                    .endObject()
                    //首次挂起时间      firstHangUpTime;
                    .startObject("firstHangUpTime")
                    .field("type", "keyword")
                    .endObject()
                    //最后解挂时间      lastRelieveHuangUpTime;
                    .startObject("lastRelieveHuangUpTime")
                    .field("type", "keyword")
                    .endObject()
                    //解挂历时      relieveHangUpLongTime;
                    .startObject("relieveHangUpLongTime")
                    .field("type", "keyword")
                    .endObject()
                    //挂起原因      causeHangUp;
                    .startObject("causeHangUp")
                    .field("type", "keyword")
                    .endObject()
                    //订单编号      orderNum;
                    .startObject("orderNum")
                    .field("type", "keyword")
                    .endObject()
                    //归档类型      fileType;
                    .startObject("fileType")
                    .field("type", "keyword")
                    .endObject()
                    //反馈      feedback;
                    .startObject("feedback")
                    .field("type", "keyword")
                    .endObject()
                    //上行光衰      upPower;
                    .startObject("upPower")
                    .field("type", "keyword")
                    .endObject()
                    //下行光衰      downPower;
                    .startObject("downPower")
                    .field("type", "keyword")
                    .endObject()
                    //OLT发送光功率      oltSendPower;
                    .startObject("oltSendPower")
                    .field("type", "keyword")
                    .endObject()
                    //OLT接收送光功率      oltReceivePower;
                    .startObject("oltReceivePower")
                    .field("type", "keyword")
                    .endObject()
                    //终端发送光功率      terminalSendPower;
                    .startObject("terminalSendPower")
                    .field("type", "keyword")
                    .endObject()
                    //终端接收送光功率      terminalReceivePower;
                    .startObject("terminalReceivePower")
                    .field("type", "keyword")
                    .endObject()

                    .endObject().endObject();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return mapping;
    }
}
