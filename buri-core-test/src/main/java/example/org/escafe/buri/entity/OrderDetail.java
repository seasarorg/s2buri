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
@Table(name = "ORDER_DETAIL")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_DETAIL_GEN")
	@SequenceGenerator(name = "ORDER_DETAIL_GEN", sequenceName = "ORDER_DETAIL_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long orderDetailId;

	@Column(precision = 10, nullable = true, unique = false)
	public Integer orderCount;

	@Column(precision = 19, nullable = true, unique = false)
	public Long itemId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long orderTitleId;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_TITLE_ID", referencedColumnName = "ORDER_TITLE_ID")
	public OrderTitle orderTitle;
}
