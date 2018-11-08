package com.scsa.prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {

	public static void main(String[] args) {
		
		final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		final String DB_USER = "scott";
		final String DB_PASSWORD = "tiger";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String userId = args[0];
		//반복해서 실행시킬 단 하나의 SQL문을 prepareStatement로 지정(변수 부분을 '?'로 쓴다.)
		//?는 %등과 함께 올 수 없다.	
		String sql = "select * from userinfo where userid=?";
		
//		String sql = "select * from userinfo where userid like '%'||?||'%'"; 
/*		String sql = "insert into userinfo(userid, password, name, email) "
				+ " values(?, ?, ?, ?)";*/
//		String sql = "delete from userinfo where userid = ?"; 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			//?에 데이터 채우기
			stmt.setString(1, userId);
			
			//sql이 select문일때
			rs = stmt.executeQuery();
			
			//sql이 select 외의 DML문일때
			//int rowcount = stmt.executeUpdate();
			
			while (rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+
						rs.getString(3)+" "+rs.getString(4));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
