package etl.orders;

import base.FileUtils;
import etl.beans.InstallOrderBean;
import init.Params;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstallOrders extends AbsOrders implements Orders {

    private String filePath;
    private String jsonPath;
    private String splitChar;
    private String isolationChar;
    private List<String> list;
    private List<List<String>> fieldList;
    private List<InstallOrderBean> beansList = new ArrayList<>();

    @Override
    public List<String> getList() {
        return list;
    }

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setJsonPath(String jsonPath){
        this.jsonPath = jsonPath;
    }

    @Override
    public void setSplitChar(String splitChar) {
        this.splitChar = splitChar;
    }

    @Override
    public void setIsolationChar(String isolationChar) {
        this.isolationChar = isolationChar;
    }

    @Override
    public void loadData() {
        this.list = super.loadData(this.filePath);
    }

    @Override
    public void loadExcelData() {
        FileUtils fileUtils = new FileUtils();
        this.list = fileUtils.read2List(this.filePath, 0);
    }

    @Override
    public void excelToList() {
    }

    @Override
    public void toList() {
        StringBuilder sb = new StringBuilder();
        for (String line : list) {
            if (line.contains("\r")) {
                line = line.replace("\r", "");
            }
            if (line.contains("\n")) {
                line = line.replace("\n", "");
            }
            if (line.contains("<th>")) {
                line = line.replace("<th>", "");
            }
            if (line.contains("</th>")) {
                line = line.replace("</th>", "");
            }
            if (line.contains("<td>")) {
                line = line.replace("<td>", "");
            }
            if (line.contains("</td>")) {
                line = line.replace("</td>", isolationChar);
            }
            if (line.contains("<div")) {
                line = line.replace("<div", "");
            }
            if (line.contains("</div>")) {
                line = line.replace("</div>", "");
            }
            if (line.contains("<tr>")) {
                line = line.replace("<tr>", "");
            }
            if (line.contains("</tr>")) {
                line = line.replace("</tr>", "\r\n");
            }
            if (line.contains("align=\"center\">")) {
                line = line.replace("align=\"center\">", "");
            }
            if (line.contains("&nbsp;")) {
                line = line.replace("&nbsp;", "");
            }
            if (line.contains("<td style=\"vnd.ms-excel.numberformat:@\">")) {
                line = line.replace("<td style=\"vnd.ms-excel.numberformat:@\">", "");
            }
            sb.append(line);
        }
        //字符串按行切分为数组后转为List
        list = Arrays.asList(sb.toString().split("\r\n"));
    }

    @Override
    public void add() {
        String str = Params.runTime + "||" + filePath;
        super.add(list,str);
    }

    @Override
    public void toBean() {
        for (List<String> line : fieldList) {
            InstallOrderBean pojo = new InstallOrderBean();
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
            pojo.setReservationTime(line.get(7));
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
            pojo.setExecutorMan(line.get(22));
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
            pojo.setLastRelieveUpTime(line.get(32));
            //解挂历时
            pojo.setRelieveUpLongTime(line.get(33));
            //挂起原因
            pojo.setCauseUp(line.get(34));
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
            //导入时间
            pojo.setImportTime(line.get(44));
            //导入文件
            pojo.setImportFile(line.get(45));

            beansList.add(pojo);
        }

    }

    @Override
    public void set() {
        for (InstallOrderBean order : beansList) {
            String id = order.getOrderNum() + "_" + order.toString().hashCode();
            order.setId(id);
            //统计值
            order.setCount(1);
            String timesTamp = order.getAcceptTime().replace(" ", "T");
            timesTamp = timesTamp + ":00.000Z";
            order.setTimesTamp(timesTamp);
            order.setImportTime(Params.runTime);
            order.setImportFile(filePath);
        }
    }

    @Override
    public void filter() {
        List<String> list = new ArrayList<>();
        for (String line : this.list) {
            int size = line.split(splitChar, -1).length;
            if (size > 10) {
                list.add(line);
            }
        }
        this.list = list;
    }

    @Override
    public void toJson() {
        this.list = super.toJson(beansList);
    }

    @Override
    public void toFieldsList() {
        fieldList = super.toFieldsList(list, splitChar);
    }

    @Override
    public void wrToFile(){
        super.wrToFile(list,jsonPath);
    }
}
