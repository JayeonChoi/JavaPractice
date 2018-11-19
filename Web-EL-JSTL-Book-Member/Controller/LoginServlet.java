package com.scsa.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scsa.model.dao.UserDAO;
import com.scsa.model.vo.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1. get parameter
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		try {
			//3. process Business Logic(call Model)
			UserDAO userDao=new UserDAO();
			User user=userDao.selectUser(id);
			
			//4. select view(move page) by result
			if (user!=null) {
				if (user.getPassword().equals(pwd)) {
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					request.setAttribute("message", user.getUserId()+" 님 로그인 되었습니다.!!");
					request.getRequestDispatcher("/Result.jsp").forward(request, response);
					return;
				} else {
					System.out.println("로그인 실패");
					request.setAttribute("Error", "비밀번호가 일치하지 않습니다.");
				}
			}  
			else {
				System.out.println("로그인 실패");
				request.setAttribute("Error", "아이디가 존재하지 않습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", "로그인 처리중 문제가 발생하였습니다. : "+e.getMessage());
		}
		
		request.getRequestDispatcher("/Error.jsp").forward(request, response);
		return;
	}

}
