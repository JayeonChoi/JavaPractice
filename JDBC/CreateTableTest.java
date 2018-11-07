package com.scsa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableTest {

	public static void main(String[] args) {
		
		final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		final String DB_USER = "scott";
		final String DB_PASSWORD = "tiger";
		Connection conn = null;
		Statement stmt = null;
		String sql = "create table userinfo( " 
					+ "userid varchar2(12) primary key, "
					+ "password varchar2(20) not null,"
					+ "name varchar2(30) not null,"
					+ "email varchar2(50) )";
		
		try {
			//1. Driver Class Loading
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//2. create connection
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			//3. create statement
			stmt = conn.createStatement();
			
			//4.execute SQL
			boolean flag = stmt.execute(sql);
			System.out.println(flag); 
			//result_set이 있으면 true, 아니면 false..

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. Release
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}
