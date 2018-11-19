package com.scsa.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	private static DataSource ds;
	
	static {
		Context initContext;
		try {
			initContext = new InitialContext(); //톰캣 시스템 네이밍서비스의 루트
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/OracleDB");
			
			//ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/OracleDB"); 위에 두줄을 이어서 쓴 것과 동일
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	//close는 풀 만들기 전과 바꾸지 않아도 되지만 내부 구현은 달라져있다.
	public static void close(ResultSet rs) {
		if (rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(Statement stmt) {
		if (stmt!=null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void close(Connection conn) {
		if (conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
