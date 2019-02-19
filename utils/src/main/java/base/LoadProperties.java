package base;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by jlgaoyuan on 2018/11/8.
 *
 */
public class LoadProperties {
    /**
     * 加载配置文件到map
     * @param fileName 配置文件名
     * @return map
     */
    public static boolean paramMap(String fileName, Map<String, String> map) {
        Properties props = new Properties();
        try {
            props.load(new InputStreamReader(LoadProperties.class.getClassLoader().getResourceAsStream(fileName)));
            for (Object key : props.keySet()) {
                String keyString = String.valueOf(key);  //Key
                String valString = props.getProperty(keyString);//Value
                map.put(keyString, valString);
            }
        } catch (Exception e) {
            Log.error(e.getMessage());
            Log.error("Load Config Failed :" + fileName);
            return false;
        }
        return true;
    }

    /**
     * 加载配置文件到map  3级别map    *
     *
     * @param fileName 配置文件名
     * @return map
     */
    public static boolean paramMapL3(String fileName, Map<String, Map<String, Map<String, String>>> map) {

        Properties props = new Properties();
        try {
            props.load(new InputStreamReader(LoadProperties.class.getClassLoader().getResourceAsStream(fileName)));
            for (Object key : props.keySet()) {
                String keyString = String.valueOf(key);  //Key
                String valString = props.getProperty(keyString);//Value
                if (!keyString.contains(".")) {
                    return false;
                }
                String keys[] = keyString.split(".",3);
                if (map.containsKey(keys[0])) {//如果包含一级key
                    if (map.get(keys[0]).containsKey(keys[1])) {//包含二级key
                        map.get(keys[0]).get(keys[1]).put(keys[2], valString);//添加三级key
                    } else {
                        map.get(keys[0]).put(keys[1], new HashMap<>());
                        map.get(keys[0]).get(keys[1]).put(keys[2], valString);//添加三级key
                    }
                } else { //不包含一级key
                    map.put(keys[0], new HashMap<>());//创建一级key
                    if (map.get(keys[0]).containsKey(keys[1])) {//包含二级key
                        map.get(keys[0]).get(keys[1]).put(keys[2], valString);//添加三级key
                    } else {//不包含二级key
                        map.get(keys[0]).put(keys[1], new HashMap<>());
                        map.get(keys[0]).get(keys[1]).put(keys[2], valString);//添加三级key
                    }
                }

            }
        } catch (Exception e) {
            Log.error(e.getMessage());
            Log.error("Load Config Failed :" + fileName);
            return false;
        }
        return true;

    }

}
