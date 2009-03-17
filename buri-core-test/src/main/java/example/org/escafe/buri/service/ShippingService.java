package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.ShippingNames.orderTitle;
import static example.org.escafe.buri.names.ShippingNames.shippingId;
import static example.org.escafe.buri.names.ShippingNames.shippingItemList;
import static org.seasar.extension.jdbc.operation.Operations.asc;
import static org.seasar.extension.jdbc.operation.Operations.eq;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import org.apache.commons.beanutils.BeanMap;

import example.org.escafe.buri.dto.ShippingFindDto;
import example.org.escafe.buri.entity.OrderDetail;
import example.org.escafe.buri.entity.OrderTitle;
import example.org.escafe.buri.entity.Shipping;
import example.org.escafe.buri.entity.ShippingItem;

/**
 * {@link Shipping}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class ShippingService extends AbstractService<Shipping> {
	/**
	 * {@link ShippingItemService}です。
	 */
	public ShippingItemService shippingItemService;

	/**
	 * 識別子でエンティティを検索します。
	 * 
	 * @param shippingId
	 *            識別子
	 * @return エンティティ
	 */
	public Shipping findById(Long shippingId) {
		return select().id(shippingId).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 * 
	 * @return エンティティのリスト
	 */
	public List<Shipping> findAllOrderById() {
		return select().orderBy(asc(shippingId())).getResultList();
	}

	public Shipping convertOrNew(Long shippingId) {
		if (shippingId == null) {
			throw new IllegalArgumentException("shippingId is null.");
		}
		Shipping result = new Shipping();
		Shipping entity =
		    select()
		        .leftOuterJoin(shippingItemList())
		        .id(shippingId)
		        .getSingleResult();
		return entity == null ? result : entity;
	}

	public Shipping convertOrNew(OrderTitle orderTitle) {
		if (orderTitle == null) {
			throw new IllegalArgumentException("orderTitle is null.");
		}
		if (orderTitle.orderTitleId == null) {
			throw new IllegalArgumentException(
			    "orderTitle.orderTitleId is null.");
		}
		Shipping result = new Shipping();
		Shipping entity =
		    select()
		        .leftOuterJoin(orderTitle())
		        .where(eq(orderTitle().orderTitleId(), orderTitle.orderTitleId))
		        .getSingleResult();
		if (entity == null) {
			result.customerId = orderTitle.customerId;
			result.orderTitleId = orderTitle.orderTitleId;
			for (OrderDetail od : orderTitle.orderDetailList) {
				ShippingItem si = new ShippingItem();
				si.orderDetailId = od.orderDetailId;
				result.shippingItemList.add(si);
			}
			return result;
		}
		result = convertOrNew(entity.shippingId);
		return result;
	}

	public List<Shipping> findByIds(List<Long> shippingIds) {
		return select().where(in(shippingId(), shippingIds)).getResultList();
	}

	public List<Shipping> find(ShippingFindDto dto, List<String> paths) {
		BeanMap bm = new BeanMap();
		bm.put("dto", dto);
		bm.put("paths", paths);
		return selectBySqlFile(Shipping.class, "", bm).getResultList();
	}

	@Override
	public int insert(Shipping entity) {
		int result = super.insert(entity);
		if (result == 1) {
			for (ShippingItem shippingItem : entity.shippingItemList) {
				shippingItem.shippingId = entity.shippingId;
				shippingItemService.insert(shippingItem);
			}
		}
		return result;
	}

	@Override
	public int update(Shipping entity) {
		int result = super.update(entity);
		if (result == 1) {
			for (ShippingItem shippingItem : entity.shippingItemList) {
				if (shippingItem.shippingItemId != null) {
					shippingItemService.update(shippingItem);
				} else {
					shippingItemService.insert(shippingItem);
				}
			}
		}
		return result;
	}
}