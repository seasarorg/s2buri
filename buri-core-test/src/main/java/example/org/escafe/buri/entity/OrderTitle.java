package example.org.escafe.buri.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.seasar.framework.util.tiger.CollectionsUtil;

@Entity
@Table(name = "ORDER_TITLE")
public class OrderTitle {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_TITLE_GEN")
	@SequenceGenerator(name = "ORDER_TITLE_GEN", sequenceName = "ORDER_TITLE_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long orderTitleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, unique = false)
	public Date orderDate;

	@Column(precision = 19, nullable = true, unique = false)
	public Long customerId;

	@Column(precision = 10, nullable = true, unique = false)
	public Integer status;

	@OneToMany(mappedBy = "orderTitle")
	public List<OrderDetail> orderDetailList = CollectionsUtil.newArrayList();
}
