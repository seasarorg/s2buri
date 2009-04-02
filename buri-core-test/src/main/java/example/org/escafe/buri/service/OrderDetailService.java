package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.OrderDetailNames.orderDetailId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.entity.OrderDetail;

/**
 * {@link OrderDetail}のサービスクラスです。
 * 
 * @author j5ik2o
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class OrderDetailService extends AbstractService<OrderDetail> {
	/**
	 * IDから{@link OrderDetail}エンティティを検索し返します。
	 * 
	 * @param orderDetailId
	 *            {@link OrderDetail}のID
	 * @return {@link OrderDetail}
	 */
	public OrderDetail getOrderDetail(Long orderDetailId) {
		return select().id(orderDetailId).getSingleResult();
	}

	/**
	 * 複数のIDから{@link OrderDetail}エンティティを検索し返します。
	 * 
	 * @param orderDetailIds
	 *            {@link OrderDetail}のIDのリスト
	 * @return {@link OrderDetail}のリスト
	 */
	public List<OrderDetail> getOrderDetailByIds(List<Long> orderDetailIds) {
		return select()
		    .where(in(orderDetailId(), orderDetailIds))
		    .getResultList();
	}
}
