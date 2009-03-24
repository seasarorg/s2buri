package org.escafe.buri.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractBuriPathDataEntity {
	@Column(precision = 19, nullable = false, unique = false)
	public Long pathId;

	@Column(length = 100, nullable = false, unique = false)
	public String pathName;

	@Column(length = 100, nullable = false, unique = false)
	public String realPathName;

	@Column(precision = 19, nullable = true, unique = false)
	public Long pathType;

	@Column(precision = 19, nullable = true, unique = false)
	public Long pkeyNum;

	@Column(length = 200, nullable = true, unique = false)
	public String pkeyVal;

	@Column(length = 200, nullable = false, unique = false)
	public String dataType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date autoRunTime;

	@Column(precision = 19, nullable = true, unique = false)
	public Long dataId;

	@Column(precision = 19, nullable = false, unique = false)
	public Long stateId;
}
