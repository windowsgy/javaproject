package etl.dimensionality;

import java.util.List;

public class AddFields {

    public static void accessOrders (List<String> list,String str){
        for(int i = 0 ; i < list.size();i++){
            list.set(i,list.get(i)+str);
        }
    }
}


