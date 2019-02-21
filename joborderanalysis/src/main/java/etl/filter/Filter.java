package etl.filter;


import java.util.ArrayList;
import java.util.List;

public class Filter {
    public static List<String> accessOrders(List<String> list){

        List<String> newList = new ArrayList<>();
        for (String line : list) {
            int size = line.split("\\|\\|", -1).length;
            if (size > 2) {
                newList.add(line);
            }
        }
        return newList;
    }
}
