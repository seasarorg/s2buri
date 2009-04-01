package org.escafe.buri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BURI_DATA")
public class BuriDataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_DATA_GEN")
	@SequenceGenerator(name = "BURI_DATA_GEN", sequenceName = "BURI_DATA_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long dataId;

	@Column(length = 250, nullable = true, unique = false)
	public String pkeyVal;

	@Column(precision = 19, nullable = true, unique = false)
	public Long pkeyNum;

	@Column(length = 200, nullable = false, unique = false)
	public String dataType;

	@Column(length = 200, nullable = true, unique = false)
	public String tableName;

	@Column(precision = 19, nullable = true, unique = false)
	public Long insertUserId;
}
