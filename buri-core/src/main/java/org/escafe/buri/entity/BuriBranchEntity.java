package org.escafe.buri.entity;

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
import javax.persistence.Version;

@Entity
@Table(name = "BURI_BRANCH")
public class BuriBranchEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_BRANCH_GEN")
	@SequenceGenerator(name = "BURI_BRANCH_GEN", sequenceName = "BURI_BRANCH_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long branchId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long parentBranchId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long pathId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long dataId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long btId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, unique = false)
	public Date processDate = null;

	@Version
	@Column(precision = 19, nullable = false, unique = false)
	public Long versionNo;
}
