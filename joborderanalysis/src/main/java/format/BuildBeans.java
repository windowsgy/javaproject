package format;

import java.util.ArrayList;
import java.util.List;

class BuildBeans {

    /**
     * excelList转换为 pojo
     * @return List
     */
    static List<OrderBeans> accessOrders(List<List<String>> list){
        List<OrderBeans> orderPojos = new ArrayList<>();
        for (List<String> line : list) {
            OrderBeans pojo = new OrderBeans();
            // 客户经理
            pojo.setSalesman(line.get(0));
            // 当当快
            pojo.setEfficient(line.get(1));
            // 小区名称
            pojo.setResidenceCommunity(line.get(2));
            //装机地址
            pojo.setAddress(line.get(3));
            //接入号
            pojo.setAccessNum(line.get(4));
            //客户名称
            pojo.setClientName(line.get(5));
            //状态
            pojo.setStatus(line.get(6));
            //预约上门时间
            pojo.setMakeServiceTime(line.get(7));
            //任务结束时间
            pojo.setTaskEndTime(line.get(8));
            //受理时间
            pojo.setAcceptTime(line.get(9));
            //联系人
            pojo.setLinkMan(line.get(10));
            //联系电话
            pojo.setLinkPhoneNum(line.get(11));
            //产品
            pojo.setProduct(line.get(12));
            //业务类型
            pojo.setBusinessType(line.get(13));
            //商品名称
            pojo.setCommodityName(line.get(14));
            //标准地址
            pojo.setStandAdd(line.get(15));
            //楼道交换机端口
            pojo.setLanPort(line.get(16));
            //EPON端口
            pojo.setEponPort(line.get(17));
            //ONU设备名称及端口
            pojo.setOnuPort(line.get(18));
            //分光器端口
            pojo.setOdnPort(line.get(19));
            //分光器名称
            pojo.setOdnName(line.get(20));
            //退单原因
            pojo.setReturnReason(line.get(21));
            //施工岗(人)/部门
            pojo.setExecutor(line.get(22));
            //最后回单操作人
            pojo.setFinalOperator(line.get(23));
            //客户分群
            pojo.setClientGroup(line.get(24));
            //备注
            pojo.setRemarks(line.get(25));
            //渠道名称
            pojo.setChannelName(line.get(26));
            //10000号受理渠道标志
            pojo.setTabFor10000(line.get(27));
            //原安装地址
            pojo.setInstallAddSource(line.get(28));
            //原标准地址
            pojo.setStandAddSource(line.get(29));
            //带宽
            pojo.setBandwidth(line.get(30));
            //首次挂起时间
            pojo.setFirstHangUpTime(line.get(31));
            //最后解挂时间
            pojo.setLastRelieveHuangUpTime(line.get(32));
            //解挂历时
            pojo.setRelieveHangUpLongTime(line.get(33));
            //挂起原因
            pojo.setCauseHangUp(line.get(34));
            //订单编号
            pojo.setOrderNum(line.get(35));
            //归档类型
            pojo.setFileType(line.get(36));
            //反馈
            pojo.setFeedback(line.get(37));
            //上行光衰
            pojo.setUpPower(line.get(38));
            //下行光衰
            pojo.setDownPower(line.get(39));
            //OLT发送光功率
            pojo.setOltSendPower(line.get(40));
            //OLT接收送光功率
            pojo.setOltReceivePower(line.get(41));
            //终端发送光功率
            pojo.setTerminalSendPower(line.get(42));
            //终端接收送光功率
            pojo.setTerminalReceivePower(line.get(43));

            orderPojos.add(pojo);
        }
        return orderPojos;
    }

}
