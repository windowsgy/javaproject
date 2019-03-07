package base;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 转换工具
 */
public class TranslationUtils {

    /**
     * beanToJson
     * @param obj beanObj
     * @return jsonString
     */
    public String beanToJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(obj);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * jsonToMap
     *
     * @param listJson listJson
     * @return map
     */
    public List<Map<String, Object>> jsonToMap(List<String> listJson) {
        List<Map<String, Object>> newList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (String aListJson : listJson) {
            Map map = null;
            try {
               // System.out.println(aListJson);
                map = mapper.readValue(aListJson, Map.class);
                newList.add(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newList;
    }

    /**
     * beanToMap
     *
     * @param <T> bean
     * @return map
     */
    public <T> List<Map<String, Object>> beanToMap(List<T> list) {
        List<Map<String, Object>> newList = new ArrayList<>();
        for (T aList : list) {
            Object obj = aList;
            if (obj == null) {
                return null;
            }
            Map<String, Object> map = new HashMap<>();
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                    String key = property.getName();
                    // 过滤class属性
                    if (!key.equals("class")) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(obj);
                        map.put(key, value);
                    }
                }
            } catch (Exception e) {
                System.out.println("transBean2Map Error " + e);
            }
            newList.add(map);
        }
        return newList;
    }
}
