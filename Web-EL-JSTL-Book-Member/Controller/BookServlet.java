package com.scsa.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scsa.exception.ExistedIDException;
import com.scsa.model.dao.BookDAO;
import com.scsa.model.vo.Book;

public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO bd = new BookDAO();
		request.setCharacterEncoding("utf-8");
		String booknum = request.getParameter("booknum");
		String title = request.getParameter("title");
		String sort = request.getParameter("sort");
		String country = request.getParameter("country");
		if (country==null) country="";
		String date = request.getParameter("date");
		if (date==null) date="";
		String pub = request.getParameter("publisher");
		if (pub==null) pub="";
		String writer = request.getParameter("writer");
		int price=0;
		if (request.getParameter("price")!="") {
			price = Integer.parseInt(request.getParameter("price"));
		}
		String desc = request.getParameter("desc");
		
		Book book = new Book(booknum, title, sort, country, date, pub, writer, price, desc);
		try {
			bd.insertBook(book);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ExistedIDException e) {
			e.printStackTrace();
		}
		request.setAttribute("book", book);
		RequestDispatcher rd = request.getRequestDispatcher("/book/BookView.jsp");
		rd.forward(request, response);
	}

}
