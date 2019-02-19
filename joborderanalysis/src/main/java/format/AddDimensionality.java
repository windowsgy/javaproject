package format;

import java.util.List;

/**
 * 升维
 * 根据已有字段进行升维
 */
public class AddDimensionality {

    public static  void run(List<OrderBeans> list){
        for(int i = 0 ; i < list.size();i++){
            OrderBeans orderBeans = list.get(i);
            orderBeans.setId(orderBeans.getOrderNum());
        }
    }
}
