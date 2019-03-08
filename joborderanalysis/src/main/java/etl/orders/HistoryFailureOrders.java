package etl.orders;


import etl.addFields.AddFields;
import etl.beans.HistoryInstallOrderBean;
import etl.utils.SetFields;
import init.Params;

import java.util.ArrayList;
import java.util.List;

public class HistoryFailureOrders extends AbsOrders implements Orders {

    private String filePath;
    private String splitChar;
    private String isolationChar;
    private List<String> list;
    private List<List<String>> fieldList;
    private List<HistoryInstallOrderBean> beansList = new ArrayList<>();

    @Override
    public List<String> getList() {
        return list;
    }

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
    public void loadData() {
        this.list = super.loadData(this.filePath);
    }

    @Override
    public void loadExcelData() {
        this.fieldList = super.loadExcelData(this.filePath);
    }

    @Override
    public void excelToList(){
        this.list = super.excelToList(fieldList,isolationChar);
    }

    @Override
    public void toList() {

    }

    @Override
    public void add() {
        AddFields addFields = new etl.addFields.HistoryInstallOrders();
        String addStr = addFields.run(filePath);
        super.add(list, addStr);
    }

    @Override
    public void toBean() {
        for (List<String> line : fieldList) {
            HistoryInstallOrderBean pojo = new HistoryInstallOrderBean();
            pojo.setProduct(line.get(0));
            pojo.setIncludeOrderNum(line.get(1));
            pojo.setNetwork(line.get(2));
            pojo.setOrderNum(line.get(3));
            pojo.setAccessNum(line.get(4));
            pojo.setSpeed(line.get(5));
            pojo.setSalesman(line.get(6));
            pojo.setOrderDisposeTimeLong(SetFields.toDouble(line.get(7)));
            pojo.setAddress(line.get(8));
            pojo.setStandAdd(line.get(9));
            pojo.setArea(line.get(10));
            pojo.setClientName(line.get(11));
            pojo.setLinkMan(line.get(12));
            pojo.setLinkPhoneNum(line.get(13));
            pojo.setCommodityName(line.get(14));
            pojo.setExecuteType(line.get(15));
            pojo.setExecuteAction(line.get(16));
            pojo.setIsPrint(line.get(17));
            pojo.setRemark(line.get(18));
            pojo.setProgramCtl(line.get(19));
            pojo.setStatus(line.get(20));
            pojo.setPriority(line.get(21));
            pojo.setExecutor(line.get(22));
            pojo.setExecuteDepartment(line.get(23));
            pojo.setUpTime(line.get(24));
            pojo.setFeedbackTime(line.get(25));
            pojo.setReservationStartTime(line.get(26));
            pojo.setReservationEndTime(line.get(27));
            pojo.setReservationWarnTime(line.get(28));
            pojo.setTaskEndTime(line.get(29));
            pojo.setAcceptTime(line.get(30));
            pojo.setChannelName(line.get(31));
            pojo.setArriveTime(line.get(32));
            pojo.setCompletedTime(line.get(33));
            pojo.setIsOverTime(line.get(34));
            pojo.setOrderTimeLong(SetFields.toDouble(line.get(35)));
            pojo.setLanPort(line.get(36));
            pojo.setEponPort(line.get(37));
            pojo.setOnuPort(line.get(38));
            pojo.setOdnPort(line.get(39));
            pojo.setOdnName(line.get(40));
            pojo.setVindicatePattern(line.get(41));
            pojo.setPartnerNumber(line.get(42));
            pojo.setPartnerName(line.get(43));
            pojo.setPartnerId(line.get(44));
            pojo.setExecuteTimeLong(SetFields.toDouble(line.get(45)));
            pojo.setCauseUp(line.get(46));
            pojo.setCauseFeedback(line.get(47));
            pojo.setTheDayDefer(SetFields.yesOrNo(line.get(48)));
            pojo.setFileType(line.get(49));
            pojo.setTheDay(SetFields.yesOrNo(line.get(50)));
            beansList.add(pojo);
        }
    }

    @Override
    public void set() {
        for (HistoryInstallOrderBean order: beansList) {
            String id = order.getOrderNum() + "_" + order.toString().hashCode();
            order.setId(id);
            //统计值
            order.setCount(1);
            String timesTamp = order.getAcceptTime().replace(" ", "T");
            timesTamp = timesTamp + "00Z";
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
}
