package etl;

import base.*;
import etl.orders.Orders;
import init.Params;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ETL {
    /**
     * ETL 方法
     * @return 返回转换后的数据
     */
    public static Map<String, List<String>> run() {
        Map<String,List<String>> map = new HashMap<>();
        FileUtils fileUtils = new FileUtils();
        List<String> filesNameList = fileUtils.getFilesName(Params.sourcePath);
        for (String fileName : filesNameList) {
            String filePath = Params.sourcePath + fileName;
            String jsonPath = Params.jsonPath + fileName;
            try {
                Orders orders = (Orders) Class.forName(Params.ordersClassPath).newInstance();
                orders.setFilePath(filePath);
                orders.setJsonPath(jsonPath);
                orders.setSplitChar(Params.splitChar);
                orders.setIsolationChar(Params.isolationChar);
                if ("Excel".equals(Params.fileFormat)) {
                    Log.info("load excel data");
                    orders.loadExcelData();
                    Log.info("dataList");
                    orders.excelToList();
                } else {
                    Log.info("load data");
                    orders.loadData();
                    Log.info("dataList");
                    orders.toList();
                }
                Log.info("filter");
                orders.filter();
                Log.info("addFields");
                orders.add();
                Log.info("toFieldsList");
                orders.toFieldsList();
                Log.info("toBean");
                orders.toBean();
                Log.info("set");
                orders.set();
                Log.info("toJson");
                orders.toJson();
                Log.info("write to File");
                orders.wrToFile();
                map.put(fileName, orders.getList());
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }
}
