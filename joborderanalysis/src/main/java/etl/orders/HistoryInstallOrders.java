package etl.orders;


import base.StringUtils;
import etl.beans.HistoryInstallOrderBean;
import init.Params;

import java.util.ArrayList;
import java.util.List;

public class HistoryInstallOrders extends AbsOrders implements Orders {

    private String filePath;//源文件路径
    private String jsonPath;//json文件路径
    private String splitChar;
    private String isolationChar;
    private List<String> list;
    private List<List<String>> fieldList;
    private List<HistoryInstallOrderBean> beansList = new ArrayList<>();


    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

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
        fieldList.remove(0);
        fieldList.remove(0);
        fieldList.remove(0);
        for (List<String> aFieldList : fieldList) {
            aFieldList.remove(0);
            aFieldList.remove(1);
            aFieldList.remove(6);
            aFieldList.remove(6);
            aFieldList.remove(10);
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
        super.add(list,str);
    }

    @Override
    public void toBean() {
        StringUtils stringUtils = new StringUtils();
        for (List<String> line : fieldList) {
            HistoryInstallOrderBean pojo = new HistoryInstallOrderBean();
            pojo.setProduct(line.get(0));
            pojo.setIncludeOrderNum(line.get(1));
            pojo.setNetwork(line.get(2));
            pojo.setOrderNum(line.get(3));
            pojo.setAccessNum(line.get(4));
            pojo.setSpeed(line.get(5));
            pojo.setSalesman(line.get(6));
            pojo.setOrderDisposeTimeLong(stringUtils.toDouble(line.get(7)));
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
            pojo.setOrderTimeLong(stringUtils.toDouble(line.get(35)));
            pojo.setLanPort(line.get(36));
            pojo.setEponPort(line.get(37));
            pojo.setOnuPort(line.get(38));
            pojo.setOdnPort(line.get(39));
            pojo.setOdnName(line.get(40));
            pojo.setVindicatePattern(line.get(41));
            pojo.setPartnerNumber(line.get(42));
            pojo.setPartnerName(line.get(43));
            pojo.setPartnerId(line.get(44));
            pojo.setExecuteTimeLong(stringUtils.toDouble(line.get(45)));
            pojo.setCauseUp(line.get(46));
            pojo.setCauseFeedback(line.get(47));
            pojo.setTheDayDefer(line.get(48));
            pojo.setFileType(line.get(49));
            pojo.setTheDay(line.get(50));
            beansList.add(pojo);
        }
    }

    @Override
    public void set() {
        for (HistoryInstallOrderBean order : beansList) {
            StringUtils stringUtils = new StringUtils();
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
            //标准L6地址
            String standAdd = order.getStandAdd();

            int i  ;//L6地址索引
            if(standAdd.contains("小区")){
                i = standAdd.indexOf("小区")+2;
            }else {
                i = stringUtils.indexForLetterOrDigit(standAdd);
            }
            String standAddL6 = standAdd.substring(0, i);
            order.setStandAddL6(standAddL6);
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
