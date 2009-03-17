/*
 * 作成日: 2006/05/01
 *
 */
package org.escafe.buri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BURI_USER")
public class BuriUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_BRANCH_GEN")
	@SequenceGenerator(name = "BURI_BRANCH_GEN", sequenceName = "BURI_USER_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long buriUserId;

	@Column(length = 100, nullable = true, unique = false)
	public String userIdVal;

	@Column(precision = 19, nullable = true, unique = false)
	public Long userIdNum;

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/buriUserId=").append(buriUserId);
		buff.append("/userIdVal=").append(userIdVal);
		buff.append("/userIdNum=").append(userIdNum);
		buff.append("]");
		return buff.toString();
	}
}
