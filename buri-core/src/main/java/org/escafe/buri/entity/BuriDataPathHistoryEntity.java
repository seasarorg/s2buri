package org.escafe.buri.entity;

import java.sql.Timestamp;
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

@Entity
@Table(name = "BURI_DATA_PATH_HISTORY")
public class BuriDataPathHistoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_DATA_PATH_HISTORY_GEN")
	@SequenceGenerator(name = "BURI_DATA_PATH_HISTORY_GEN", sequenceName = "BURI_DATA_PATH_HISTORY_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long historyId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long pathId;

	@Column(length = 200, nullable = true, unique = false)
	public String pathName;

	@Column(precision = 19, nullable = true, unique = false)
	public Long dataId;

	@Column(length = 50, nullable = true, unique = false)
	public String action;

	@Column(precision = 19, nullable = true, unique = false)
	public Long buriUserId;

	@Column(precision = 19, nullable = false, unique = false)
	public Long btId = Long.valueOf(0);

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date insertDate = new Timestamp((new Date()).getTime());

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/historyId=").append(historyId);
		buff.append("/pathName=").append(pathName);
		buff.append("/pathId=").append(pathId);
		buff.append("/dataId=").append(dataId);
		buff.append("/action=").append(action);
		buff.append("/buriUserId=").append(buriUserId);
		buff.append("/btId=").append(btId);
		buff.append("/insertDate=").append(insertDate);
		buff.append("]");
		return buff.toString();
	}
}
