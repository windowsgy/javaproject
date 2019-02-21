package etl;

import base.*;
import etl.beans.AccessOrderBeans;
import etl.dimensionality.Add;
import etl.filter.Filter;
import etl.structTransfer.StruTransfer;
import etl.structuring.BuildBeans;
import etl.transition.Trans;

import java.util.List;

public class Etl {

    /**
     * ETL 方法
     *
     * @param souFilePath 源文件路径
     * @param desFilePath 目的文件路径
     * @return 返回转换后的数据
     */
    public static String run(String souFilePath, String desFilePath) {
        FileUtils fileUtils = new FileUtils();
        StringUtils stringUtils = new StringUtils();
        ListUtils listUtils = new ListUtils();

        Log.info("load file is souFilePath ");
        List<String> list = fileUtils.read2List(souFilePath, 0);
        Log.info("file transition");
        String str = Trans.accessOrders(list);
        Log.info("string to Array");
        list = stringUtils.stringToList(str);//字符串转数组
        Log.info("filter");
        list = Filter.accessOrders(list);//过滤
        //切分字段生成List
        Log.info("fields splint");
        List<List<String>> listFields = listUtils.list2ListFields(list, "\\|\\|");
        //build beans
        Log.info("to bean");
        List<AccessOrderBeans> orderBeansList = BuildBeans.accessOrders(listFields);
        //增加维度
        Log.info("add");
        Add.accessOrders(orderBeansList);
        //数据结构转换
        List<String> listJson = StruTransfer.beanToJson(orderBeansList);
        //List转换为字符串
        Log.info("to json");
        String jsonString = listUtils.list2String(listJson);
        //创建文件并写入
        fileUtils.createFile(desFilePath);
        fileUtils.wrStr2File(str, desFilePath);
        return jsonString;
    }
}
