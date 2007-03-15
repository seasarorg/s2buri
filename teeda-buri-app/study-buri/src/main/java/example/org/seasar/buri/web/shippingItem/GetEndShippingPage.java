package example.org.seasar.buri.web.shippingItem;

import java.util.List;

import example.org.seasar.buri.bao.ShippingItemBao;
import example.org.seasar.buri.dao.ShippingItemDao;

public class GetEndShippingPage {

	private ShippingItemBao shippingItemBao;

	private long orderDetailId;

	private long shippingId;

	private String shippingItemIndex;

	private List shippingItemItems;

	/**
	 * 
	 */
	private ShippingItemDao shippingItemDao;

	public long getOrderDetailId() {
		return this.orderDetailId;
	}

	public void setOrderDetailId(long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public long getShippingId() {
		return this.shippingId;
	}

	public void setShippingId(long shippingId) {
		this.shippingId = shippingId;
	}

	public String getShippingItemIndex() {
		return this.shippingItemIndex;
	}

	public void setShippingItemIndex(String shippingItemIndex) {
		this.shippingItemIndex = shippingItemIndex;
	}

	public List getShippingItemItems() {
		return this.shippingItemItems;
	}

	public void setShippingItemItems(List shippingItemItems) {
		this.shippingItemItems = shippingItemItems;
	}

	public String initialize() {
		return null;
	}

	/**
	 * @return the shippingItemDao
	 */
	public ShippingItemDao getShippingItemDao() {
		return this.shippingItemDao;
	}

	/**
	 * @param shippingItemDao
	 *            the shippingItemDao to set
	 */
	public void setShippingItemDao(ShippingItemDao shippingItemDao) {
		this.shippingItemDao = shippingItemDao;
	}

	public String prerender() {
		this.setShippingItemItems(shippingItemBao.getEndShipping());
		return null;
	}

	public ShippingItemBao getShippingItemBao() {
		return this.shippingItemBao;
	}

	public void setShippingItemBao(ShippingItemBao shippingItemBao) {
		this.shippingItemBao = shippingItemBao;
	}

}
