package org.escafe.buri.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.seasar.nadejako.annotation.NakoProperties;
import org.seasar.nadejako.annotation.NakoProperty;

@NakoProperties( {
    @NakoProperty(name = "あいでぃー", property = "userId"),
    @NakoProperty(name = "ユーザ名", property = "userName"),
    @NakoProperty(name = "権限名", property = "roleName"),
    @NakoProperty(name = "上司", property = "parentUserId") })
@Entity
@Table(name = "BURI_TEST_USER")
public class BuriTestUser {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BURI_TEST_USER_GEN")
	@SequenceGenerator(name = "BURI_TEST_USER_GEN", sequenceName = "BURI_TEST_USER_SEQ", allocationSize = 1)
	@Column(precision = 19, nullable = false, unique = true)
	public Long userId;

	public Long getUserId() {
		return userId;
	}

	@Column(length = 100, nullable = false, unique = false)
	public String userName = "";

	@Column(length = 100, nullable = false, unique = false)
	public String roleName = "";

	@Version
	@Column(precision = 19, nullable = true, unique = false)
	public Long parentUserId;
}
