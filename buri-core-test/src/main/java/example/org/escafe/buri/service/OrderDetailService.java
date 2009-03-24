package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.OrderDetailNames.orderDetailId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.entity.OrderDetail;

/**
 * {@link OrderDetail}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class OrderDetailService extends AbstractService<OrderDetail> {
	public OrderDetail getOrderDetail(Long orderDetailId) {
		return select().id(orderDetailId).getSingleResult();
	}

	public List<OrderDetail> getOrderDetailByIds(List<Long> orderDetailIds) {
		return select()
		    .where(in(orderDetailId(), orderDetailIds))
		    .getResultList();
	}
}
