/*
 * 作成日: 2006/05/18
 *
 */
package org.escafe.buri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BURI_PATH_DATA_USER")
public class BuriPathDataUserEntity extends AbstractBuriPathDataEntity {
	@Column(precision = 19, nullable = false, unique = false)
	public Long buriUserId;

	@Column(length = 100, nullable = false, unique = false)
	public String userIdVal;

	@Column(precision = 19, nullable = false, unique = false)
	public Long userIdNum;

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/pathId=").append(pathId);
		buff.append("/pathName=").append(pathName);
		buff.append("/realPathName=").append(realPathName);
		buff.append("/pathType=").append(pathType);
		buff.append("/pkeyNum=").append(pkeyNum);
		buff.append("/pkeyVal=").append(pkeyVal);
		buff.append("/dataType=").append(dataType);
		buff.append("/dataId=").append(dataId);
		buff.append("/stateId=").append(stateId);
		buff.append("/autoRunTime=").append(autoRunTime);
		buff.append("/buriUserId=").append(buriUserId);
		buff.append("/userIDNum=").append(userIdNum);
		buff.append("/userIDVal=").append(userIdVal);
		buff.append("]");
		return buff.toString();
	}
}
