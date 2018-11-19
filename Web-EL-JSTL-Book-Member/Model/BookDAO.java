package com.scsa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scsa.exception.ExistedIDException;
import com.scsa.model.vo.Book;
import com.scsa.util.DBUtil;

public class BookDAO {
	public void insertBook(Book b) throws SQLException, ExistedIDException {
		insertBook(b.getBooknum(), b.getTitle(), b.getSort(), b.getCountry(), b.getDate(),
				b.getPublisher(), b.getWriter(), b.getPrice(), b.getDesc());
	}

	public void insertBook(String isbn, String title, String sort, String nation, String date,
			String pub, String author, int price, String desc)
			throws SQLException, ExistedIDException {
		Connection conn = null;
		PreparedStatement stmt = null;

		if (search(isbn)!=null) throw new ExistedIDException(isbn);
		String sql = "insert into bookinfo " + "values(?,?,?,?,?,?,?,?,?)";

		try {
			conn = DBUtil.getConnection();

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, isbn);
			stmt.setString(2, title);
			stmt.setString(3, sort);
			stmt.setString(4, nation);
			stmt.setString(5, date);
			stmt.setString(6, pub);
			stmt.setString(7, author);
			stmt.setInt(8, price);
			stmt.setString(9, desc);
			stmt.executeUpdate();


		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public void deleteBook(String booknum) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from bookinfo where isbn = ?";

		try {
			// 2. create connection
			conn = DBUtil.getConnection();
			// 3. create statement
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, booknum);
			// 4. execute(send) SQL
			stmt.executeUpdate();
		} finally {
			// 5. resource release
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	public Book search(String isbn) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Book b = null;
		String sql = "select * " + "from bookinfo where isbn = ? ";

		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, isbn);
			rs = stmt.executeQuery();

			if (rs.next()) {
				b = new Book();
				b.setBooknum(rs.getString(1));
				b.setTitle(rs.getString(2));
				b.setSort(rs.getString(3));
				b.setCountry(rs.getString(4));
				b.setDate(rs.getString(5));
				b.setPublisher(rs.getString(6));
				b.setWriter(rs.getString(7));
				b.setPrice(rs.getInt(8));
				b.setDesc(rs.getString(9));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return b;
	}

	public List<Book> GetBooklist() throws SQLException {
		ArrayList<Book> list = new ArrayList<Book>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "select * from bookinfo";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
						rs.getInt(8), rs.getString(9)));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}
}
