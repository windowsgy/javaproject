package etl.orders;


import base.StringUtils;
import etl.addFields.AddFields;
import etl.beans.HistoryInstallOrderBean;
import etl.utils.SetFields;
import init.Params;

import java.util.ArrayList;
import java.util.List;

public class HistoryInstallOrders extends AbsOrders implements Orders {

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

        }
    }

    @Override
    public void set() {
        for (HistoryInstallOrderBean order: beansList) {
            StringUtils stringUtils = new StringUtils();
            //id
            order.setId(order.getOrderNum() + "_" + order.toString().hashCode());
            //统计值
            order.setCount(1);
            //时间戳
            order.setTimesTamp(order.getAcceptTime().replace(" ", "T")+ "00Z");
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
            String standAddL6 = standAdd.substring(0,stringUtils.indexForLetterOrDigit(standAdd));
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
}
