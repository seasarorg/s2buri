package org.escafe.buri.service;

import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;

public class BuriIdListService {
	public JdbcManager jdbcManager;

	private String sqlFilePathPrefix =
	    "META-INF/sql/org/escafe/buri/BuriIdList";

	public List<Long> getIdListByPathName(String className, String pathName,
	        Long pathType) {
		class Param {
			@SuppressWarnings("unused")
			public String className;

			@SuppressWarnings("unused")
			public String pathName;

			@SuppressWarnings("unused")
			public Long pathType;
		}
		Param param = new Param();
		param.className = className;
		param.pathName = pathName;
		param.pathType = pathType;
		return jdbcManager.selectBySqlFile(
		    Long.class,
		    sqlFilePathPrefix + "getIdListByPathName.sql",
		    param).getResultList();
	}

	public List<Long> getIdListByPathNameAndUser(String className,
	        String pathName, Long pathType, Long userId) {
		class Param {
			@SuppressWarnings("unused")
			public String className;

			@SuppressWarnings("unused")
			public String pathName;

			@SuppressWarnings("unused")
			public Long pathType;

			@SuppressWarnings("unused")
			public Long userId;
		}
		Param param = new Param();
		param.className = className;
		param.pathName = pathName;
		param.pathType = pathType;
		param.userId = userId;
		return jdbcManager.selectBySqlFile(
		    Long.class,
		    sqlFilePathPrefix + "getIdListByPathNameAndUser.sql",
		    param).getResultList();
	}
}
