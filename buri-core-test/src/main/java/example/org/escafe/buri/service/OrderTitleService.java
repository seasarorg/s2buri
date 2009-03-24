package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.OrderTitleNames.orderDetailList;
import static example.org.escafe.buri.names.OrderTitleNames.orderTitleId;
import static org.seasar.extension.jdbc.operation.Operations.asc;

import java.util.List;

import example.org.escafe.buri.entity.OrderDetail;
import example.org.escafe.buri.entity.OrderTitle;

/**
 * {@link OrderTitle}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class OrderTitleService extends AbstractService<OrderTitle> {
	/**
	 * {@link OrderDetailService}です。
	 */
	public OrderDetailService orderDetailService;

	/**
	 * 識別子でエンティティを検索します。
	 * 
	 * @param orderTitleId
	 *            識別子
	 * @return エンティティ
	 */
	public OrderTitle findById(Long orderTitleId) {
		return select().id(orderTitleId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 * 
	 * @return エンティティのリスト
	 */
	public List<OrderTitle> findAllOrderById() {
		return select().orderBy(asc(orderTitleId())).getResultList();
	}

	public OrderTitle findByIdWithOrderDetail(Long orderTitleId) {
		return select()
		    .leftOuterJoin(orderDetailList())
		    .id(orderTitleId)
		    .getSingleResult();
	}

	@Override
	public int insert(OrderTitle entity) {
		int result = super.insert(entity);
		if (result == 1) {
			for (OrderDetail od : entity.orderDetailList) {
				od.orderTitleId = entity.orderTitleId;
				orderDetailService.insert(od);
			}
		}
		return result;
	}

	@Override
	public int update(OrderTitle entity) {
		int result = super.update(entity);
		if (result == 1) {
			for (OrderDetail od : entity.orderDetailList) {
				od.orderTitleId = entity.orderTitleId;
				if (od.orderTitleId != null) {
					orderDetailService.update(od);
				} else {
					orderDetailService.insert(od);
				}
			}
		}
		return result;
	}

	@Override
	public List<OrderTitle> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
}
