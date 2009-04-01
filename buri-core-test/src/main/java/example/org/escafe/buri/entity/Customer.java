package example.org.escafe.buri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_GEN")
	@SequenceGenerator(name = "CUSTOMER_GEN", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long customerId;

	@Column(length = 100, nullable = false, unique = false)
	public String customerName = "";
}
