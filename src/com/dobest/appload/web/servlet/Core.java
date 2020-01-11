package com.dobest.appload.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dobest.appload.constant.Constant;
import com.dobest.appload.entity.AppInfor;
import com.dobest.appload.utils.MyGson;
import com.dobest.appload.web.service.share.version.ShareVersionManager;

/**
 * Servlet implementation class Core
 */
@WebServlet("/core")
public class Core extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		request.setCharacterEncoding("UTF-8");
		ShareVersionManager shareVersionManager = new ShareVersionManager();
		AppInfor appInfor = (AppInfor)shareVersionManager.obationShare(request);
		if(appInfor!=null)
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher(Constant.JSP_PATH+appInfor.getShowurl());
			request.setAttribute("result", MyGson.getInstance().toJson(appInfor));
			appInfor.setShowurl(null);
			dispatcher.forward(request, response);
		}
		else
			response.getWriter().append("error!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
