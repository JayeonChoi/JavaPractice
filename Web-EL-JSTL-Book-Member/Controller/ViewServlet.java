package com.scsa.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scsa.model.dao.BookDAO;
import com.scsa.model.vo.Book;


public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BookDAO bd = new BookDAO();
		String isbn = request.getParameter("booknum");
		Book book=null;
		try {
			book = bd.search(isbn);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		if (book!=null) {
			request.setAttribute("book", book);
			request.getRequestDispatcher("/book/BookView.jsp").forward(request, response);
		} else {
			request.setAttribute("Error", "책을 찾을 수 없습니다.");
			request.getRequestDispatcher("/Error.jsp").forward(request, response);
		}
		
	}

}
