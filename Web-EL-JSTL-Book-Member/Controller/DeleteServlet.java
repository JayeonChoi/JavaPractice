package com.scsa.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scsa.model.dao.BookDAO;


public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BookDAO bd = new BookDAO();
		String isbn = request.getParameter("booknum");
		try {
			bd.deleteBook(isbn);
			request.setAttribute("message", isbn+" ���� ���� �Ǿ����ϴ�.");
			RequestDispatcher rd = request.getRequestDispatcher("/Result.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("Error", "���� ���� �� ������ �߻��Ͽ����ϴ�.");
			e.printStackTrace();
		}
	}

}
