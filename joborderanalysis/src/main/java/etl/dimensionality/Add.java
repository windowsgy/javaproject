package etl.dimensionality;

import etl.beans.AccessOrderBeans;

import java.util.List;

/**
 * 升维
 * 根据已有字段进行升维
 */
public class Add {

    /**
     * 接入型工单
     * @param list list
     */
    public static  void accessOrders(List<AccessOrderBeans> list){
        for (AccessOrderBeans orderBeans : list) {
            //设置ID
            orderBeans.setCount(1);
            String timesTamp = orderBeans.getAcceptTime().replace(" ","T");
            timesTamp = timesTamp+":00.000Z";
            orderBeans.setTimesTamp(timesTamp);
        }
    }
}
