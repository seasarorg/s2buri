package example.org.escafe.buri.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.seasar.framework.util.tiger.CollectionsUtil;

@Entity
@Table(name = "SHIPPING")
public class Shipping {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHIPPING_GEN")
	@SequenceGenerator(name = "SHIPPING_GEN", sequenceName = "SHIPPING_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long shippingId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, unique = false)
	public Date shippingDate = new Date(8070, 11, 31, 23, 59, 59);

	@Column(precision = 19, nullable = true, unique = false)
	public Long orderTitleId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long customerId;

	@OneToMany(mappedBy = "shipping")
	public List<ShippingItem> shippingItemList = CollectionsUtil.newArrayList();

	@ManyToOne
	@JoinColumn(name = "ORDER_TITLE_ID", referencedColumnName = "ORDER_TITLE_ID")
	public OrderTitle orderTitle;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
