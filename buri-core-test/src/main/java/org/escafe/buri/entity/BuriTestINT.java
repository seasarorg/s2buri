package org.escafe.buri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "BURI_TEST_INT")
public class BuriTestINT {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_TEST_INT_GEN")
	@SequenceGenerator(name = "BURI_TEST_INT_GEN", sequenceName = "BURI_TEST_INT_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long testId;

	@Column(length = 100, nullable = false, unique = false)
	public String value = "";

	@Version
	@Column(precision = 19, nullable = false, unique = false)
	public Long versionNo;
}
