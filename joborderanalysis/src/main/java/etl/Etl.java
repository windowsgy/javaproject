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
        Log.info("start etl");
        Map<String,List<String>> map = new HashMap<>();
        FileUtils fileUtils = new FileUtils();
        List<String> filesNameList = fileUtils.getFilesName(Params.sourcePath);
        Log.debug("source files count is :"+filesNameList.size());
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
                    Log.linel1();
                    Log.info("load excel data");
                    orders.loadExcelData();
                    Log.debug("file line count is :"+orders.getFieldList().size());
                    Log.linel1();
                    Log.info("dataList");
                    orders.excelToList();
                    Log.debug("list size is :"+orders.getList().size());
                } else {
                    Log.linel1();
                    Log.info("load data");
                    orders.loadData();
                    Log.debug("file line count is :"+orders.getFieldList().size());
                    Log.linel1();
                    Log.info("dataList");
                    Log.linel1();
                    orders.toList();
                    Log.debug("list size is :"+orders.getList().size());
                }
                Log.linel1();
                Log.info("filter");
                orders.filter();
                Log.debug("list size is :"+orders.getList().size());
                Log.linel1();
                Log.info("addFields");
                orders.add();
                Log.debug("list size is :"+orders.getList().size());
                Log.linel1();
                Log.info("toFieldsList");
                orders.toFieldsList();
                Log.debug("list size is :"+orders.getFieldList().size());
                Log.linel1();
                Log.info("toBean");
                orders.toBean();
                Log.linel1();
                Log.info("set");
                orders.set();
                Log.linel1();
                Log.info("toJson");
                orders.toJson();
                Log.linel1();
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
