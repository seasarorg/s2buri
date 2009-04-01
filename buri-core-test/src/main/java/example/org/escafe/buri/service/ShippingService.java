package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.ShippingNames.orderTitle;
import static example.org.escafe.buri.names.ShippingNames.shippingId;
import static example.org.escafe.buri.names.ShippingNames.shippingItemList;
import static org.seasar.extension.jdbc.operation.Operations.eq;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

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

	public Shipping getShipping(Long shippingId) {
		return select().id(shippingId).getSingleResult();
	}

	public List<Shipping> getShippingByIds(List<Long> shippingIds) {
		return select().where(in(shippingId(), shippingIds)).getResultList();
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

	public List<Shipping> find(ShippingFindDto dto, List<String> paths) {
		class Param {
			public ShippingFindDto dto;

			public List<String> paths;
		}
		Param param = new Param();
		param.dto = dto;
		param.paths = paths;
		return selectBySqlFile(Shipping.class, "find.sql", param)
		    .getResultList();
	}

	public List<Shipping> findAndUser(ShippingFindDto dto, List paths,
	        List userIds) {
		class Param {
			public ShippingFindDto dto;

			public List<String> paths;

			public List<Long> userIds;
		}
		Param param = new Param();
		param.dto = dto;
		param.paths = paths;
		param.userIds = userIds;
		return selectBySqlFile(Shipping.class, "findAndUser.sql", param)
		    .getResultList();
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
