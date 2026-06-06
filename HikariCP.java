package com.servlet.bank;

import java.sql.Connection;
import java.sql.SQLException;

//import org.apache.commons.dbcp2.BasicDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP {
	
private static HikariDataSource commondatasource ;// this is the common datwsaource to the whole server
	
	static { 
		
		HikariConfig config = new HikariConfig();
		
				config.setJdbcUrl("jdbc:mysql://localhost:3306/tsrbank?rewriteBatchedStatements=true");//my local mysql url
				//config.setJdbcUrl("jdbc:mysql://tsrbank-rds-instance-restored-sameaz.cvuo82ako1dw.ap-south-1.rds.amazonaws.com:3306/tsrbank");//my rds mysql url
				config.setUsername("jayadithyapraneeth");//my local and ec2 local mysql username
				//config.setUsername("jayadityapraneet");//my rds mysql username
//				config.setPassword("0000");//my local mysql password
				config.setPassword("Jayadithya@123");//my ec2 local mysql password
				//config.setPassword("praneeth");//my rds mysql password
				config.setDriverClassName("com.mysql.cj.jdbc.Driver");//java database connection driver for mysql database, not only for DBCP but also for HikariCP, it is the same driver class name for both connection pool libraries
				
				//commondatasource.setInitialSize(20);// this is the initial size of the connection pool
				config.setMaximumPoolSize(50);// this is the maximum number of connections that can be created in the connection pool
				config.setMinimumIdle(5);// this is the maximum number of idle connections that can be kept in the connection pool
				config.setIdleTimeout(300000);// this is the minimum number of idle connections that can be kept in the connection pool
				config.setConnectionTimeout(30000);// this is the maximum time to wait for a connection from the pool before throwing an exception
			
				
				commondatasource = new HikariDataSource(config);
				
		}
	
	public static Connection getConnection() {
		
		try {	
			return commondatasource.getConnection();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			return null;
		}
		
		
		
	}

}
