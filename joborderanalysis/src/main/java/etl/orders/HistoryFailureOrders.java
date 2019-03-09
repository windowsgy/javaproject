package etl.orders;


import etl.beans.HistoryInstallOrderBean;

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
    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
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
        String str = Params.runTime + "||" + filePath;
        super.add(list,str);
    }

    @Override
    public void toBean() {
        for (List<String> line : fieldList) {

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
    public void wrToFile() {
        super.wrToFile(list,jsonPath);

    }

    @Override
    public void toFieldsList() {
        fieldList = super.toFieldsList(list, splitChar);
    }
}
