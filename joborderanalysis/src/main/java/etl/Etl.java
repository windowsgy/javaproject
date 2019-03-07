package etl;

import base.*;
import etl.orders.Orders;
import init.Params;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ETL {

    /**
     * ETL 方法
     *
     * @param souFilePath 源文件路径
     * @param formatFilePath 目的文件路径
     * @param jsonFilePath 目的文件路径
     * @return 返回转换后的数据
     */
    public static Map<String, List<String>> run() {
        Map<String, List<String>> map = new HashMap();
        FileUtils fileUtils = new FileUtils();
        List<String> filesNameList = fileUtils.getFilesName(Params.sourcePath);
        Iterator var3 = filesNameList.iterator();

        while(var3.hasNext()) {
            String fileName = (String)var3.next();
            String filePath = Params.sourcePath + fileName;

            try {
                Orders orders = (Orders)Class.forName(Params.ordersClassPath).newInstance();
                orders.setFilePath(filePath);
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
                Log.info("attribute add");
                orders.add();
                Log.info("toFieldsList");
                orders.toFieldsList();
                Log.info("bean");
                orders.toBean();
                Log.info("set");
                orders.set();
                Log.info("toJson");
                orders.toJson();
                map.put(fileName, orders.getList());
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException var7) {
                var7.printStackTrace();
            }
        }

        return map;
    }
}
