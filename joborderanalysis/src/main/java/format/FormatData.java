package format;

import base.*;
import buildParams.Params;

import java.util.List;

public class FormatData {

    /**
     * 输入数据路径格式化方法
     * 程序启动输入数据路径参数，根据参数格式化EXCEL文件
     * @return boolean
     */
    public static boolean run(){
        Log.info("run FormatData");
        FileUtils fileUtils = new FileUtils();
        ListUtils listUtils = new ListUtils();
        JsonUtils jsonUtils = new JsonUtils();
        List<String> fileList = fileUtils.getFilesName(Params.sourcePath);
        for (String aFileList : fileList) {
            //文件路径
            String fileFullPath = Params.sourcePath+aFileList;
            Log.info("load file is:"+ fileFullPath);
            //数据格式转换
            Log.info("file transition");
            String str = OrderTransition.accessOrders(fileFullPath);
            Log.info("string to Array");
            List<String> list = StringUtils.stringToList(str);//字符串转数组
            Log.info("filter");
            list = FilterData.run(list);//过滤
            //切分字段生成List
            Log.info("fields splint");
            List<List<String>> listFields = listUtils.list2ListFields(list,"\\|\\|");
            //build beans
            Log.info("to bean");
            List<OrderBeans> orderBeansList = BuildBeans.run(listFields);
            //增加维度
            AddDimensionality.run(orderBeansList);
            //build json
            Log.info("to json");
            List<String> listJson =  jsonUtils.listBeanToListJson(listFields);//BuildJSON.run(orderBeansList);
            //文件全路径
            String jsonFileFullPath = Params.jsonPath+aFileList;
            fileUtils.createFile(jsonFileFullPath);
            String jsonString = listUtils.list2String(listJson);
            fileUtils.wrStr2File(jsonString, jsonFileFullPath);
        }
        return true;
    }
}
