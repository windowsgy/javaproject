package base;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public List<String> stringToList (String str){

        String [] strArray = str.split("\r\n");
        List<String> list = Arrays.asList(strArray);
        return list;
    }


}
