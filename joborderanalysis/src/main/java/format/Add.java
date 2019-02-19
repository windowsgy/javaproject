package format;

import java.util.List;

/**
 * 升维
 * 根据已有字段进行升维
 */
class Add {

    /**
     * 接入型工单
     * @param list list
     */
    static  void accessOrders(List<OrderBeans> list){
        for (OrderBeans orderBeans : list) {
            //设置ID
            orderBeans.setId(orderBeans.getOrderNum());
        }
    }
}
