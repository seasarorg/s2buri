/*
 * 作成日: 2006/05/12
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
import javax.persistence.Version;

@Entity
@Table(name = "BURI_TRANSACTION")
public class BuriTransactionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_TRANSACTION_GEN")
	@SequenceGenerator(name = "BURI_TRANSACTION_GEN", sequenceName = "BURI_TRANSACTION_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long btId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public Date insertDate;

	@Version
	@Column(precision = 19, nullable = false, unique = false)
	public Long versionNo;

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("[");
		buff.append("/btId=").append(btId);
		buff.append("/insertDate=").append(insertDate);
		buff.append("/versionNo=").append(versionNo);
		buff.append("]");
		return buff.toString();
	}
}
