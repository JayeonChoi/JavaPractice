package com.scsa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectDate {

	public static void main(String[] args) {
		final String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		final String user = "hr";
		final String password = "hr";
		final String driver = "oracle.jdbc.driver.OracleDriver";
		
		Connection cnn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String sql1 = "select * from Employees where "
				+ "to_char(hire_date, 'yyyy')='2007'";
		String sql2 = "select * from Employees where "
				+ "trunc(hire_date, 'yyyy') = '2007/01/01'";
		String sql3 = "select * from Employees where "
				+ "hire_date like '07%'";
		
		try {
			Class.forName(driver);
			cnn = DriverManager.getConnection(url, user, password);
			st = cnn.createStatement();
			rs = st.executeQuery(sql3);
			while (rs.next()) {
				for (int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
					System.out.print(rs.getString(i)+" ");
				}
				System.out.println();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
