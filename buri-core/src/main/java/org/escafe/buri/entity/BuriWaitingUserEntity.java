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
@Table(name = "BURI_WAITING_USER")
public class BuriWaitingUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_WAITING_USER_GEN")
	@SequenceGenerator(name = "BURI_WAITING_USER_GEN", sequenceName = "BURI_WAITING_USER_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long waitingUserId;

	@Column(precision = 19, nullable = false, unique = false)
	public Long waitingId;

	@Column(precision = 19, nullable = false, unique = false)
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
		buff.append("/waitingUserID=").append(waitingUserId);
		buff.append("/waitingID=").append(waitingId);
		buff.append("/buriUserID=").append(buriUserId);
		buff.append("/insertDate=").append(insertDate);
		buff.append("/deleteDate=").append(deleteDate);
		buff.append("]");
		return buff.toString();
	}
}
