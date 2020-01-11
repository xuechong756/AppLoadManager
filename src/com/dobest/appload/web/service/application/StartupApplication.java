package com.dobest.appload.web.service.application;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.dobest.appload.constant.Constant;

public class StartupApplication extends HttpServlet{
	private static final long serialVersionUID = 7051021631338790613L;
	public void init(ServletConfig request) throws ServletException
	{
		contextInitialized();
		
	}
	
	//设置log放置路径
	public void contextInitialized() {
        System.setProperty("log4jdir", Constant.LOG_ROOT_PATH);
    }
}
