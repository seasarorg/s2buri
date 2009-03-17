/*
 * 作成日: 2006/05/18
 *
 */
package org.escafe.buri.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BURI_PATH_DATA")
public class BuriPathDataEntity extends AbstractBuriPathDataEntity {
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
		buff.append("]");
		return buff.toString();
	}
}
