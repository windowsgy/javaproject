package format;


import java.util.ArrayList;
import java.util.List;

public class FilterData {
    public static List<String> run(List<String> list){

        List<String> newList = new ArrayList<>();
        for(int i = 0 ;i < list.size();i++){
            String line = list.get(i);
            int size = line.split("\\|\\|",-1).length;
            if(size > 2) {
                newList.add(line);
            }
        }
        return newList;
    }
}
