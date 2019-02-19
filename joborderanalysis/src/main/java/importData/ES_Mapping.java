package importData;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class ES_Mapping {
    /**
     * 创建索引映射
     *
     * @param indexName indexName
     * @param typeName  type
     * @param client    es client
     */

    public static void buildIndexMap(String indexName, String typeName, Client client) {
        PutMappingRequest mapping = Requests.putMappingRequest(indexName)
                .type(typeName).source(getAccessOrdersMapping());
        client.admin().indices().putMapping(mapping).actionGet();

    }

    /**
     * 构建接入型工单mapping
     *
     * @return XcontentBuilder
     */
    private static XContentBuilder getAccessOrdersMapping() {
        XContentBuilder mapping = null;
        try {
            mapping = jsonBuilder()
                    .startObject().startObject("properties")
                    // 客户经理 salesman;
                    .startObject("salesman")
                    .field("type", "text")
                    .field("index", false).endObject()
                    // 当当快 efficient;
                    .startObject("efficient")
                    .field("type", "text")
                    .field("index", false).endObject()
                    // 小区名称 residenceCommunity;
                    .startObject("residenceCommunity")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //装机地址 address;
                    .startObject("address").field("type", "text")
                    .field("index", false).endObject()
                    //接入号 accessNum;
                    .startObject("accessNum")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //客户名称 clientName;
                    .startObject("clientName")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //状态 status;
                    .startObject("status")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //预约上门时间 makeServiceTime;
                    .startObject("makeServiceTime")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //任务结束时间 taskEndTime;
                    .startObject("taskEndTime")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //受理时间 acceptTime;
                    .startObject("acceptTime")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //联系人 linkMan;
                    .startObject("lineMan")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //联系电话 linkPhoneNum;
                    .startObject("linkPhoneNum")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //产品 product;
                    .startObject("product")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //业务类型 businessType;
                    .startObject("businessType")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //商品名称 commodityName;
                    .startObject("commodityName")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //标准地址 standAdd;
                    .startObject("standAdd")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //楼道交换机端口 lanPort;
                    .startObject("lanPort")
                    .field("type", "text")
                    .field("index", false).endObject()
                    // EPON端口 eponPort;
                    .startObject("eponPort")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //ONU设备名称及端口 onuPort;
                    .startObject("onuPort")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //分光器端口 odnPort;
                    .startObject("odnPort")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //分光器名称 odnName;
                    .startObject("odnName")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //退单原因 returnReason;
                    .startObject("returnReason")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //施工岗(人)/部门 executor;
                    .startObject("executor")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //最后回单操作人 finalOperator;
                    .startObject("finalOperator")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //客户分群      clientGroup;
                    .startObject("clientGroup")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //备注      remarks;
                    .startObject("remarks")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //渠道名称      channelName;
                    .startObject("channelName")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //10000号受理渠道标志      tabFor10000;
                    .startObject("tabFor10000")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //原安装地址      installAddSource;
                    .startObject("installAddSource")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //原标准地址      standAddSource;
                    .startObject("standAddSource")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //带宽      bandwidth;
                    .startObject("bandwidth")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //首次挂起时间      firstHangUpTime;
                    .startObject("firstHangUpTime")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //最后解挂时间      lastRelieveHuangUpTime;
                    .startObject("lastRelieveHuangUpTime")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //解挂历时      relieveHangUpLongTime;
                    .startObject("relieveHangUpLongTime")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //挂起原因      causeHangUp;
                    .startObject("causeHangUp")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //订单编号      orderNum;
                    .startObject("orderNum")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //归档类型      fileType;
                    .startObject("fileType")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //反馈      feedback;
                    .startObject("feedback")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //上行光衰      upPower;
                    .startObject("upPower")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //下行光衰      downPower;
                    .startObject("downPower")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //OLT发送光功率      oltSendPower;
                    .startObject("oltSendPower")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //OLT接收送光功率      oltReceivePower;
                    .startObject("oltReceivePower")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //终端发送光功率      terminalSendPower;
                    .startObject("terminalSendPower")
                    .field("type", "text")
                    .field("index", false).endObject()
                    //终端接收送光功率      terminalReceivePower;
                    .startObject("terminalReceivePower")
                    .field("type", "text")
                    .field("index", false).endObject()

                    .endObject().endObject();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return mapping;
    }
}
