package com.dobest.appload.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dobest.appload.constant.Constant;
import com.dobest.appload.utils.MyGson;

/**
 * Servlet implementation class Visit
 */
@WebServlet("/share")
public class Share extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(Constant.JSP_PATH+"gather.jsp");
		request.setAttribute("query", MyGson.getInstance().toJson(toChange(request)));
		dispatcher.forward(request, response);
	}
	
	public String toChange(HttpServletRequest request)
	{
		String str = request.getQueryString();
		return str;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
