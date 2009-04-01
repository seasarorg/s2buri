package example.org.escafe.buri.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BILL")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BILL_GEN")
	@SequenceGenerator(name = "BILL_GEN", sequenceName = "BILL_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long billId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, unique = false)
	public Date billDate;

	@Column(precision = 19, nullable = true, unique = false)
	public Long shippingId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long orderTitleId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long customerId;
}
