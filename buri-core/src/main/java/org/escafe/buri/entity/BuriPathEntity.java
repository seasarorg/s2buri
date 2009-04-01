package org.escafe.buri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BURI_PATH")
public class BuriPathEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_BRANCH_GEN")
	@SequenceGenerator(name = "BURI_BRANCH_GEN", sequenceName = "BURI_PATH_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long pathId;

	@Column(length = 100, nullable = false, unique = false)
	public String pathName;

	@Column(length = 100, nullable = false, unique = false)
	public String realPathName;

	@Column(precision = 19, nullable = true, unique = false)
	public Long pathType;

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/pathId=").append(pathId);
		buff.append("/pathName=").append(pathName);
		buff.append("/realPathName=").append(realPathName);
		buff.append("/pathType=").append(pathType);
		buff.append("]");
		return buff.toString();
	}
}
