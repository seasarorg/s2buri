/*
 * 作成日: 2006/05/01
 *
 */
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

import jp.starlogic.util.datetime.DateUtil;

@Entity
@Table(name = "BURI_STATE_USER")
public class BuriStateUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_STATE_UNDO_LOG_GEN")
	@SequenceGenerator(name = "BURI_STATE_UNDO_LOG_GEN", sequenceName = "BURI_STATE_USER_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long stateUserId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long stateId;

	@Column(precision = 19, nullable = true, unique = false)
	public Long buriUserId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date insertDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date deleteDate = DateUtil.getSQLMaxDate();

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/stateUserId=").append(stateUserId);
		buff.append("/stateId=").append(stateId);
		buff.append("/buriUserId=").append(buriUserId);
		buff.append("/insertDate=").append(insertDate);
		buff.append("/deleteDate=").append(deleteDate);
		buff.append("]");
		return buff.toString();
	}
}
