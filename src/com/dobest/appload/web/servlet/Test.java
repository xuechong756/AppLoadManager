package com.dobest.appload.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dobest.appload.entity.AppInfor;
import com.dobest.appload.entity.ShareBase;
import com.dobest.appload.web.service.PageManager;
import com.google.gson.Gson;

/**
 * Servlet implementation class Test
 */
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		
		String id = request.getParameter("id");
		String msg = request.getParameter("msg");
		String pkgname = request.getParameter("pkgname");
		String os = request.getParameter("os");
		String channel = request.getParameter("chnl");
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("msg", msg);
		paramMap.put("pkgname", pkgname);
		paramMap.put("os", os);
		paramMap.put("channel", channel);
		paramMap.put("httpver", "1");
		
		ShareBase shareBase = new ShareBase();
		shareBase.setId(paramMap.get("id"));
		shareBase.setMsg(paramMap.get("msg"));
		shareBase.setPakName(paramMap.get("pkgname"));
		shareBase.setOs(paramMap.get("os"));
		shareBase.setChnl(paramMap.get("channel"));
		AppInfor appInfor = new PageManager().getAppInfor(shareBase);
		Gson gson = new Gson();
		String resultGson = gson.toJson(appInfor);
		response.getWriter().append("Served at: ").append(resultGson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
