package base;
;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public <T> List<String> listBeanToListJson(List<T> listBeans){
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> listString = new ArrayList<>();
        for (T listBean : listBeans) {
            try {
                String json = objectMapper.writeValueAsString(listBean);
                listString.add(json+"\r\n");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return listString;
    }

    /**
     * jsonToMap
     * @param listJson listJson
     * @return map
     */
    public  List<Map<String,Object>> jsonToMap(List<String> listJson){
        List<Map<String,Object>> newList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for(int i = 0 ; i < listJson.size();i++){
            Map map = null;
            try {
                map = mapper.readValue(listJson.get(i), Map.class);
                newList.add(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newList;
    }
}
