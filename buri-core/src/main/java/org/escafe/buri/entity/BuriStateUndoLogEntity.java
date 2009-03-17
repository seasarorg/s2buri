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
@Table(name = "BURI_STATE_UNDO_LOG")
public class BuriStateUndoLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_STATE_UNDO_LOG_GEN")
	@SequenceGenerator(name = "BURI_STATE_UNDO_LOG_GEN", sequenceName = "BURI_STATE_UNDO_LOG_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long stateUndoLogId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long stateId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long pathId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long dataId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long branchId;

	@Column(length = 20, nullable = true, unique = false)
	public String userIdVal = null;

	@Column(precision = 19, nullable = true, unique = false)
	public Long userIdNum = null;

	@Column(precision = 19, nullable = false, unique = false)
	public Long btId;

	@Column(precision = 19, nullable = false, unique = false)
	public Long createBtId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date insertDate = new Timestamp((new Date()).getTime());

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, unique = false)
	public Date autoRunTime =
	    (new GregorianCalendar(9999, 11, 31, 23, 59, 59)).getTime();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, unique = false)
	public Date processDate =
	    (new GregorianCalendar(9999, 11, 31, 23, 59, 59)).getTime();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date abortDate =
	    (new GregorianCalendar(9999, 11, 31, 23, 59, 59)).getTime();

	@Version
	@Column(nullable = false, unique = false)
	public int versionNo;

	public BuriStateUndoLogEntity() {
	}

	public BuriStateUndoLogEntity(BuriStateEntity dto, long newBtId) {
		stateId = dto.stateId;
		pathId = dto.pathId;
		dataId = dto.dataId;
		branchId = dto.branchId;
		userIdVal = dto.userIdVal;
		userIdNum = dto.userIdNum;
		btId = dto.btId;
		createBtId = newBtId;
		insertDate = dto.insertDate;
		autoRunTime = dto.autoRunTime;
		processDate = dto.processDate;
		abortDate = dto.abortDate;
	}

	public BuriStateEntity getBuriStateEntity() {
		BuriStateEntity dto = new BuriStateEntity();
		dto.stateId = stateId;
		dto.pathId = pathId;
		dto.dataId = dataId;
		dto.branchId = branchId;
		dto.userIdVal = userIdVal;
		dto.userIdNum = userIdNum;
		dto.btId = btId;
		dto.insertDate = insertDate;
		dto.autoRunTime = autoRunTime;
		dto.processDate = processDate;
		dto.abortDate = abortDate;
		return dto;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/StateUndoLogId=").append(stateUndoLogId);
		buff.append("/stateId=").append(stateId);
		buff.append("/pathId=").append(pathId);
		buff.append("/dataId=").append(dataId);
		buff.append("/branchId=").append(branchId);
		buff.append("/userIdNum=").append(userIdNum);
		buff.append("/userIdVal=").append(userIdVal);
		buff.append("/btId=").append(btId);
		buff.append("/createBTID=").append(createBtId);
		buff.append("/insertDate=").append(insertDate);
		buff.append("/processDate=").append(processDate);
		buff.append("/autoRunTime=").append(autoRunTime);
		buff.append("/abortDate=").append(abortDate);
		buff.append("/versionNo=").append(versionNo);
		buff.append("]");
		return buff.toString();
	}
}
