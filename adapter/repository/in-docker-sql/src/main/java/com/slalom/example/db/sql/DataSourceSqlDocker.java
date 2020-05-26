package com.slalom.example.db.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceSqlDocker {

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	static {
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://localhost:8899/clean-arch");
		config.setUsername("root");
		config.setPassword("test");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.addDataSourceProperty("type","com.zaxxer.hikari.HikariDataSource");

	}

	public DataSourceSqlDocker() {
	}

	public static Connection getConnection() {
		try {
			ds = new HikariDataSource(config);
			return ds.getConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Error to connect to databaase", ex);
		}
	}
}
