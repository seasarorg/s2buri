package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.OrderDetailNames.orderDetailId;
import static org.seasar.extension.jdbc.operation.Operations.asc;

import java.util.List;

import example.org.escafe.buri.entity.OrderDetail;

/**
 * {@link OrderDetail}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class OrderDetailService extends AbstractService<OrderDetail> {
	/**
	 * 識別子でエンティティを検索します。
	 * 
	 * @param orderDetailId
	 *            識別子
	 * @return エンティティ
	 */
	public OrderDetail findById(Long orderDetailId) {
		return select().id(orderDetailId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 * 
	 * @return エンティティのリスト
	 */
	public List<OrderDetail> findAllOrderById() {
		return select().orderBy(asc(orderDetailId())).getResultList();
	}
}
