package example.org.escafe.buri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_GEN")
	@SequenceGenerator(name = "ITEM_GEN", sequenceName = "ITEM_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long itemId;

	@Column(length = 100, nullable = false, unique = false)
	public String itemName = "";

	@Column(precision = 19, nullable = true, unique = false)
	public Long price;
}
