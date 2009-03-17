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

@Entity
@Table(name = "BURI_STATE")
public class BuriStateEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_STATE_GEN")
	@SequenceGenerator(name = "BURI_STATE_GEN", sequenceName = "BURI_STATE_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long stateId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long pathId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long dataId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long branchId;

	@Column(length = 20, nullable = true, unique = false)
	public String userIdVal = null;

	@Column(length = 200, nullable = true, unique = false)
	public String participantName = null;

	@Column(length = 200, nullable = true, unique = false)
	public String participantType = null;

	@Column(precision = 19, nullable = true, unique = false)
	public Long userIdNum = null;

	@Column(precision = 19, nullable = true, unique = false)
	public Long btId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date insertDate = new Timestamp((new Date()).getTime());

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date autoRunTime =
	    (new GregorianCalendar(9999, 11, 31, 23, 59, 59)).getTime();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date processDate =
	    (new GregorianCalendar(9999, 11, 31, 23, 59, 59)).getTime();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date abortDate =
	    (new GregorianCalendar(9999, 11, 31, 23, 59, 59)).getTime();

	public int versionNo;

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/stateId=").append(stateId);
		buff.append("/pathId=").append(pathId);
		buff.append("/dataId=").append(dataId);
		buff.append("/branchId=").append(branchId);
		buff.append("/userIDNum=").append(userIdNum);
		buff.append("/userIDVal=").append(userIdVal);
		buff.append("/participantName=").append(participantName);
		buff.append("/participantType=").append(participantType);
		buff.append("/btId=").append(btId);
		buff.append("/insertDate=").append(insertDate);
		buff.append("/processDate=").append(processDate);
		buff.append("/autoRunTime=").append(autoRunTime);
		buff.append("/abortDate=").append(abortDate);
		buff.append("/versionNo=").append(versionNo);
		buff.append("]");
		return buff.toString();
	}
}
