package etl.orders;

import base.StringUtils;
import etl.beans.HistoryFailureOrderBean;

import init.Params;

import java.util.ArrayList;
import java.util.List;

public class HistoryFailureOrders extends AbsOrders implements Orders {

    private String filePath;
    private String jsonPath;
    private String splitChar;
    private String isolationChar;
    private List<String> list;
    private List<List<String>> fieldList;
    private List<HistoryFailureOrderBean> beansList = new ArrayList<>();

    @Override
    public List<String> getList() {
        return list;
    }

    @Override
    public List<List<String>> getFieldList(){return fieldList;}

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    @Override
    public void loadData() {
        this.list = super.loadData(this.filePath);
    }

    @Override
    public void loadExcelData() {
        this.fieldList = super.loadExcelData(this.filePath,isolationChar);
        for (List<String> aFieldList : fieldList) {//删除列
            aFieldList.remove(0);
            aFieldList.remove(8);
        }

    }

    @Override
    public void excelToList() {
        this.list = super.excelToList(fieldList, isolationChar);
    }

    @Override
    public void toList() {

    }

    @Override
    public void add() {
        String str = Params.runTime + "||" + filePath;
        super.add(list, str);
    }

    @Override
    public void toBean() {
        StringUtils stringUtils = new StringUtils();
        for (List<String> line : fieldList) {
            HistoryFailureOrderBean pojo = new HistoryFailureOrderBean();
            pojo.setOrderNum(line.get(0));
            pojo.setStatus(line.get(1));
            pojo.setAcceptTime(line.get(2));
            pojo.setNetwork(line.get(3));
            pojo.setBusinessNum(line.get(4));
            pojo.setBusinessType(line.get(5));
            pojo.setUserName(line.get(6));
            pojo.setUserLevel(line.get(7));
            pojo.setAddress(line.get(8));
            pojo.setDeclarePheno(line.get(9));
            pojo.setDeclarePhenoDesc(line.get(10));
            pojo.setOrdersSource(line.get(11));
            pojo.setLinkMan(line.get(12));
            pojo.setLinkPhoneNum(line.get(13));
            pojo.setRemark(line.get(14));
            pojo.setFirstResponseTime(line.get(15));
            pojo.setFirstResponseTimeLong(stringUtils.toDouble(line.get(16)));
            pojo.setFirstResponseOverTime(line.get(17));
            pojo.setReceiptTime(line.get(18));
            pojo.setReceiptCause(line.get(19));
            pojo.setPrincipal(line.get(20));
            pojo.setPrincipalPost(line.get(21));
            pojo.setAssessTimeLong(stringUtils.toDouble(line.get(22)));
            pojo.setAssessTimeLimit(line.get(23));
            pojo.setFiledTime(line.get(24));
            pojo.setFiledMan(line.get(25));
            pojo.setPreprocessor(line.get(26));
            pojo.setPreprocessorContent(line.get(27));
            pojo.setReplier(line.get(28));
            pojo.setReplyContent(line.get(29));
            pojo.setAddInfoCount(stringUtils.toLong(line.get(30)));
            pojo.setAddInfoContent(line.get(31));
            pojo.setRebuildCount(stringUtils.toLong(line.get(32)));
            pojo.setRebuildContent(line.get(33));
            pojo.setOverTime(line.get(34));
            pojo.setClientBase(line.get(35));
            pojo.setWarn(line.get(36));
            pojo.setControlOverTime(line.get(37));
            pojo.setUrgeRepair(line.get(38));
            pojo.setFeedbackCount(stringUtils.toLong(line.get(39)));
            pojo.setFeedbackContent(line.get(40));
            pojo.setUrgeRepairCount(stringUtils.toLong(line.get(41)));
            pojo.setUrgeRepairContent(line.get(42));
            pojo.setAutoReturnVisitResult(line.get(43));
            pojo.setAutoReturnVisitStatus(line.get(44));
            pojo.setCustomerSatisfaction(line.get(45));
            pojo.setRepairTimeLong(stringUtils.toDouble(line.get(46)));
            pojo.setSpeed(line.get(47));
            pojo.setAccessWay(line.get(48));
            pojo.setTheDay(line.get(49));
            pojo.setTheDayAgreement(line.get(50));
            beansList.add(pojo);
        }
    }

    @Override
    public void set() {
        for (HistoryFailureOrderBean order : beansList) {
            //id
            order.setId(order.getOrderNum() + "_" + order.toString().hashCode());
            //统计值
            order.setCount(1);
            //时间戳
            order.setTimesTamp(order.getAcceptTime().replace(" ", "T") + "00Z");
            //导入时间
            order.setImportTime(Params.runTime);
            //导入数据文件路径
            order.setImportFile(filePath);
            //year
            order.setYear(order.getAcceptTime().split("-")[0]);
            //month
            order.setMonth(order.getAcceptTime().split("-")[1]);
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
    public void wrToFile() {
        super.wrToFile(list, jsonPath);

    }

    @Override
    public void toFieldsList() {
        fieldList = super.toFieldsList(list, splitChar);
    }
}
