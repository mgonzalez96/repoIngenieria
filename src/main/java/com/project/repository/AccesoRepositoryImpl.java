package com.project.repository;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AccesoRepositoryImpl extends JdbcDaoSupport {

	public AccesoRepositoryImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

}
