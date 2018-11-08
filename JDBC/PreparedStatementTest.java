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
		//�ݺ��ؼ� �����ų �� �ϳ��� SQL���� prepareStatement�� ����(���� �κ��� '?'�� ����.)
		//?�� %��� �Բ� �� �� ����.	
		String sql = "select * from userinfo where userid=?";
		
//		String sql = "select * from userinfo where userid like '%'||?||'%'"; 
/*		String sql = "insert into userinfo(userid, password, name, email) "
				+ " values(?, ?, ?, ?)";*/
//		String sql = "delete from userinfo where userid = ?"; 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			stmt = conn.prepareStatement(sql);
			
			//?�� ������ ä���
			stmt.setString(1, userId);
			
			//sql�� select���϶�
			rs = stmt.executeQuery();
			
			//sql�� select ���� DML���϶�
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
