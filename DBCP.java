package com.servlet.bank;

import java.sql.Connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class DBCP {//this class just have an objective to create a connection pool and provide the server with the connection whenever needed
	
	private static volatile BasicDataSource commondatasource = new BasicDataSource(); // this is the common datwsaource to the whole server
	
	static { 
				commondatasource.setUrl("jdbc:mysql://localhost:3306/tsrbank");//my local mysql url
				//commondatasource.setUrl("jdbc:mysql://tsrbank-rds-instance-restored-sameaz.cvuo82ako1dw.ap-south-1.rds.amazonaws.com:3306/tsrbank");//my rds mysql url
				commondatasource.setUsername("jayadithyapraneeth");//my local and ec2 local mysql username
				//commondatasource.setUsername("jayadityapraneet");//my rds mysql username
//				commondatasource.setPassword("0000");//my local mysql password
				commondatasource.setPassword("Jayadithya@123");//my ec2 local mysql password
				//commondatasource.setPassword("praneeth");//my rds mysql password
				commondatasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			
				commondatasource.setInitialSize(20);// this is the initial size of the connection pool
				commondatasource.setMaxTotal(50);// this is the maximum number of connections that can be created in the connection pool
				commondatasource.setMaxIdle(5);// this is the maximum number of idle connections that can be kept in the connection pool
				commondatasource.setMinIdle(2);// this is the minimum number of idle connections that can be kept in the connection pool
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
