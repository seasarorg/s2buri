package org.escafe.buri.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

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
@Table(name = "BURI_JOIN_WAITING")
public class BuriJoinWaitingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_JOIN_WAITING_GEN")
	@SequenceGenerator(name = "BURI_JOIN_WAITING_GEN", sequenceName = "BURI_WAITING_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long waitingId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long pathId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long dataId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long branchId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long btId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date insertDate = new Timestamp((new Date()).getTime());

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date processDate =
	    (new GregorianCalendar(9999, 11, 31, 23, 59, 59)).getTime();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date abortDate =
	    (new GregorianCalendar(9999, 11, 31, 23, 59, 59)).getTime();

	@Version
	@Column(precision = 10, nullable = true, unique = false)
	public int versionNo;
}
