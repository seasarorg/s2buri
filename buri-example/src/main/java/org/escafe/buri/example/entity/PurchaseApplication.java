package org.escafe.buri.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class PurchaseApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PURCHASE_APPLICATION_GEN")
	@SequenceGenerator(name = "PURCHASE_APPLICATION_GEN", sequenceName = "PURCHASE_APPLICATION_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long purchaseApplicationId;

	@Column(length = 255, nullable = false, unique = false)
	public String applicantName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date applicationDate;

	@Column(length = 255, nullable = false, unique = false)
	public String productName;

	@Column(precision = 10, nullable = false, unique = true)
	public Integer amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date createDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date updateDate;

	@Version
	@Column(precision = 19, nullable = false, unique = false)
	public Long versionNo;

}
