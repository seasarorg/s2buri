package example.org.escafe.buri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SHIPPING_ITEM")
public class ShippingItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHIPPING_ITEM_GEN")
	@SequenceGenerator(name = "SHIPPING_ITEM_GEN", sequenceName = "SHIPPING_ITEM_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long shippingItemId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long orderDetailId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long shippingId;

	@ManyToOne
	@JoinColumn(name = "SHIPPING_ID", referencedColumnName = "SHIPPING_ID")
	public Shipping shipping;
}
