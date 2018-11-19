package com.scsa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scsa.model.dao.UserDAO;
import com.scsa.model.vo.User;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userDao=new UserDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		try {
			if (action.equalsIgnoreCase("login")) {
				login(request, response);
			}
			else if (action.equalsIgnoreCase("logout")) {
				logout(request, response);
			}
			else if (action.equalsIgnoreCase("list")) {
				list(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}	
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		ArrayList<String> list = new ArrayList<String>();

		if (id==null || id.trim().length()==0) {
			System.out.println("아이디 입력 오류");
			list.add("아이디는 반드시 입력되어야 합니다.");
			}
		if (pwd==null || pwd.trim().length()==0) {
			System.out.println("비밀번호 입력 오류");
			list.add("비밀번호는 반드시 입력되어야 합니다.");
		}
		
		if (list.size()>0) {
			request.setAttribute("errorMessage", list);
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			return;
		}	
		
		try {
			
			User user=userDao.selectUser(id);
			
			if (user!=null) {
				if (user.getPassword().equals(pwd)) {
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					ServletContext app = getServletContext();
					
					Integer loginCount = (Integer)app.getAttribute("loginCount");
					if (loginCount==null) {
						app.setAttribute("loginCount", new Integer(1));
					} else {
						int count = loginCount.intValue();
						app.setAttribute("loginCount", count+1);
					}
					response.sendRedirect(request.getContextPath()+"/main.jsp");
					return;
				} else {
					System.out.println("로그인 실패1");
					request.setAttribute("failMessage", "비밀번호가 일치하지 않습니다.");
					request.getRequestDispatcher("/fail.jsp").forward(request, response);
				}
			}  
			else {
				System.out.println("로그인 실패2");
				request.setAttribute("failMessage", "아이디가 존재하지 않습니다.");
				request.getRequestDispatcher("/fail.jsp").forward(request, response);
			}

		} catch (SQLException e) {
			System.out.println("로그인 실패3");
			request.setAttribute("error", "로그인 처리중 문제가 발생하였습니다.");
			throw e;
		}
		
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return;
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			List<User> userList = userDao.selectUserList();
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/user/list.jsp").forward(request, response);
			return;
		} catch (SQLException e) {
			request.setAttribute("error", "사용자 목록 조회처리 중 문제가 발생하였습니다. : "+e.getMessage());
			throw e;
		} 
		
	}
}
