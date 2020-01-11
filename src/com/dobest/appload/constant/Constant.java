package com.dobest.appload.constant;

import com.dobest.appload.utils.GlobalUtils;

public class Constant {

	//调试模式 true, false:非调试模式
	public static final boolean DEBUG_MOD = true;
	
	//基地址
	private static final String BASE_PATH = GlobalUtils.getWebInfPath();

	// 根目录
	public static final String ROOT_PATH = BASE_PATH + "WEB-INF/db/";

	// 运行日志存放位置
	public static final String LOG_ROOT_PATH = BASE_PATH + "logs/AppLoadManager_log";
	
	//app 应用信息
	public static final String APP_INFOR = ROOT_PATH + "t_appinfor/";
	
	//jsp放置路径
	public static final String JSP_PATH = "WEB-INF/jsp/";
	
	//找不对应下载渠道，使用默认渠道
	public static final String DEFAULT_CHANNEL="xiaomi";
	
	//android deeplink 配置
	//platform:平台代号，android为1，ios为2
	//pakName:
	//msg:
	public static String getDeepLink(String platform, String pakName, String msg)
	{
		switch(platform)
		{
			case "1": return "dbt-"+pakName+"//pakName?msg="+msg;
			case "2": return "dbt"+pakName+":/msg="+msg;
			default:return null;
		}
	}

	// 正则验证 日期格式， mm-dd
	public static final String DATE_REX = "\\d{4}";

	private Constant() {
	}
}
