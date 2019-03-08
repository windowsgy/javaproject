package etl.orders;

import base.ExcelUtils;
import base.FileUtils;
import base.ListUtils;
import base.TranslationUtils;
import etl.beans.OrderBean;
import init.Params;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract class AbsOrders implements Orders {

    /**
     * 加载爬虫数据方法
     */
    List<String> loadData(String filePath) {
        FileUtils fileUtils = new FileUtils();
        return fileUtils.read2List(filePath, 0);
    }

    /**
     * 加载导出数据方法
     */
    List<List<String>> loadExcelData(String filePath) {
        File xlsFile = new File(filePath);
        ExcelUtils excelUtils = new ExcelUtils();
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(xlsFile);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return excelUtils.sheetToList(wb, 0);
    }

    /**
     * excelList List<List<String>> 转换为 List<String>
     *
     * @param list          excel List
     * @param isolationChar isolationChar
     * @return List<String>
     */
    List<String> excelToList(List<List<String>> list, String isolationChar) {
        ListUtils listUtils = new ListUtils();
        return listUtils.listFields2List(list, isolationChar);
    }

    /**
     * 添加字段方法
     *
     * @param str string
     */
    void add(List<String> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + str);
        }
    }

    /**
     * 结构体List 转字JSON符串
     */
    <T> List<String> toJson(List<T> list) {
        TranslationUtils translationUtils = new TranslationUtils();
        List<String> listJson = new ArrayList<>();
        for (T beans : list) {
            String json = translationUtils.beanToJson(beans);
            listJson.add(json);
        }
        return listJson;
    }

    List<List<String>> toFieldsList(List<String> list, String isolationChar) {
        ListUtils listUtils = new ListUtils();
        return listUtils.list2ListFields(list, isolationChar);
    }
}
