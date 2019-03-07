package base;


import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by jlgaoyuan on 2018/5/5.
 * List 工具
 */
public class ListUtils {

    /**
     * 根据map 中的value进行判断， 返回所有 key List
     *
     * @param map   map
     * @param value 查找的value
     * @return list
     */
    public  List<String> keyList(Map<String, String> map, String value) {
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key).equals(value)) {
                list.add(key);
            }
        }
        return list;
    }

    /**
     * List To List Array
     *
     * @param list      List
     * @param splitChar SplitChar
     * @return List Array
     */
    public List<String[]> list2ListArray(List<String> list, String splitChar) {
        String[] firstArray = list.get(0).split(splitChar,-1); //首行
        int firstArraySize = firstArray.length;
        //  LogInfo.info("First Array Size:"+firstArraySize);
        List<String[]> listArray = new ArrayList<>();
        int lineCount = 0;
        for (String aList : list) {
            lineCount++;
            String[] array = aList.split(splitChar,-1);
            if (array.length != firstArraySize) {
                Log.info(lineCount + " :Line Error : " + aList);
                return null;
            }
            listArray.add(aList.split(splitChar));
        }
        return listArray;
    }

    /**
     * list<String> 转换 List<List<String>>>
     * @param list List
     * @param splitChar 分隔符
     * @return 行、列 列表
     */
    public List<List<String>> list2ListFields(List<String> list, String splitChar) {
        int firstArraySize = list.get(0).split(splitChar,-1).length; //首行
        List<List<String>> theList = new ArrayList<>();
        int lineCount = 0;
        for (String lists : list) {
            lineCount++;
            String[] array = lists.split(splitChar,-1);
            if (array.length == firstArraySize) {// 如果此行长度等于首行字段长度
                List<String> listFields = new ArrayList<>();
                for (String anArray : array) {
                    listFields.add(anArray.trim());
                }
                theList.add(listFields);
            } else {
                Log.error(lineCount + " :Line Error , Current Line Split Lenght : " + lists);
                return null;
            }
        }
        return theList;
    }

    /**
     * List<List<String>>> 转换 list<String>
     * @param list List
     * @param splintChar 分隔符
     * @return 行、列 列表
     */
    public List<String> listFields2List(List<List<String>> list,String splintChar){
        List<String> theList = new ArrayList<>();
        for (List<String> subList : list) {
            StringBuilder sb = new StringBuilder();
            for(String field : subList){
                sb.append(field+splintChar);
            }
            sb.append("\r\n");
            theList.add(sb.toString());
        }
        return theList;
    }


    /**
     * List 选择，选择符合规则的数据
     * @param list  List
     * @param regex Regex
     * @return List
     */
    public static List<String> listSelect(List<String> list, String regex) {
        List<String> newList = new ArrayList<>();
        for (String line : list) {
            if (Pattern.matches(regex, line)) {
                newList.add(line);
            }
        }
        return newList;
    }

    /**
     * List 过滤，过滤掉符合规则的数据
     * @param list  List
     * @param regex Regex
     * @return List
     */
    public List<String> listFilter(List<String> list, String regex) {
        List<String> newList = new ArrayList<>();
        for (String line : list) {
            if (!Pattern.matches(regex, line)) {
                newList.add(line);
            }
        }
        return newList;
    }

    /**
     * 选取数组中字段，添加至List ,用于选择字段
     * @param listArray List
     * @param index     index
     * @return List
     */
    public  List<String> listArrField(List<String[]> listArray, int index) {
        List<String> list = new ArrayList<>();
        for (String[] aListArray : listArray) {
            list.add(aListArray[index]);
        }
        return list;
    }

    /**
     * List 转换为 字符串
     */

    public String list2String(List<String> list){
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < list.size();i++){
            sb.append(list.get(i)+"\r\n");
        }
        return sb.toString();
    }


}
