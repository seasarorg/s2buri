/*
 * 作成日: 2005/11/29
 *
 */
package example.org.escafe.buri.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoDto extends OrderTitleDto {
    private List orderDetail = new ArrayList();

    public List getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List orderDetail) {
        this.orderDetail = orderDetail;
    }
    
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("{");
        buff.append(super.toString());
        buff.append("/orderDetail=").append(orderDetail);
        buff.append("}");
        return buff.toString();
    }
    
}
