package etl;

import base.*;
import buildParams.ParamsBase;
import etl.beans.AccessOrderBeans;
import etl.dimensionality.AddFields;
import etl.dimensionality.SetFields;
import etl.filter.Filter;
import etl.structTransfer.StructTransfer;
import etl.structuring.BuildBeans;
import etl.transfer.Transfer;

import java.util.Arrays;
import java.util.List;

public class Etl {

    /**
     * ETL 方法
     *
     * @param souFilePath 源文件路径
     * @param formatFilePath 目的文件路径
     * @param jsonFilePath 目的文件路径
     * @return 返回转换后的数据
     */
    public static List<String> run(String souFilePath,String formatFilePath, String jsonFilePath) {
        FileUtils fileUtils = new FileUtils();
        ListUtils listUtils = new ListUtils();
        Log.info("load file is souFilePath "+souFilePath);
        List<String> list = fileUtils.read2List(souFilePath, 0);
        Log.info("file transition");
        String str = Transfer.accessOrders(list);
        Log.info("String to List");
        list= Arrays.asList(str.split("\r\n"));//字符串转List
        Log.info("List Size Is :"+ list.size());
        Log.info("filter");
        list = Filter.accessOrders(list);//过滤
        Log.info("Filter List Size Is :"+ list.size());
        //增加字段
        Log.info("add fields");
        String addFields = ParamsBase.runTime+"||"+fileUtils.getFileName(souFilePath);
        AddFields.accessOrders(list,addFields);
        //切分字段生成List
        Log.info("fields splint");
        List<List<String>> listFields = listUtils.list2ListFields(list, "\\|\\|");
        Log.info("Fields List Size Is :"+ list.size());
        //List字段转换为标准结构，用于分析使用
        Log.info("List Transfer");
        String stdString = StructTransfer.listToStd(listFields);
        Log.info("write stand struct");
        fileUtils.createFile(formatFilePath);
        fileUtils.wrStr2File(stdString,formatFilePath);
        //build beans
        Log.info("build bean");
        List<AccessOrderBeans> orderBeansList = BuildBeans.accessOrders(listFields);
        //增加维度
        Log.info("set bean");
        SetFields.accessOrders(orderBeansList);
        //数据结构转换
        List<String> listJson = StructTransfer.beanToJson(orderBeansList);
        //List转换为字符串
        Log.info("to json");
        String jsonString = listUtils.list2String(listJson);
        //创建文件并写入
        Log.info("write json struct");
        fileUtils.createFile(jsonFilePath);
        fileUtils.wrStr2File(jsonString, jsonFilePath);
        return listJson;
    }
}
