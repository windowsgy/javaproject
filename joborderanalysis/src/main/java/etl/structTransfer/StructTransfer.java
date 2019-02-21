package etl.structTransfer;

import base.TranslationUtils;
import etl.beans.AccessOrderBeans;

import java.util.ArrayList;
import java.util.List;

public class StructTransfer {

    /**
     * 结构体List 转字符串
     * @param list 结构体List
     * @return list 字符串
     */
    public static List<String> beanToJson(List<AccessOrderBeans> list){
        TranslationUtils translationUtils = new TranslationUtils();
        List<String> listJson = new ArrayList<>();
        for (AccessOrderBeans beans : list) {
            listJson.add(translationUtils.beanToJson(beans));
        }
        return listJson;
    }


    /**
     * List字段转标准结构
     * @param list 字段
     * @return String
     */
    public static String listToStd(List<List<String>> list){
        StringBuilder sb = new StringBuilder();
        for (List<String> lineList : list) {
            for (String aLineList : lineList) {
                sb.append(aLineList+"||");
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }
}