package com.scsa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scsa.model.dao.BookDAO;
import com.scsa.model.vo.Book;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BookDAO bd = new BookDAO();
		try {		
			List<Book> bookList = bd.GetBooklist();
			request.setAttribute("bookList", bookList);
			request.getRequestDispatcher("/book/BookList.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

}
