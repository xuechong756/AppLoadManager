package com.dobest.appload.web.service.share.version;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dobest.appload.entity.ShareBase;
import com.dobest.appload.utils.ExtractVersion;
import com.dobest.appload.web.service.PageManager;

public class ShareVersionManager {
	
	public Object obationShare(HttpServletRequest request)
	{
		Map<String, String> paramMap = new HashMap<>();// = GlobalUtils.parseUrlParams(request);
		String id = request.getParameter("id");
		String msg = request.getParameter("msg");
		String pkgname =request.getParameter("pkgname");
		String os = request.getParameter("os");
		String channel = request.getParameter("chnl");
		paramMap.put("id", id);
		paramMap.put("msg", msg);
		paramMap.put("pkgname", pkgname);
		paramMap.put("os", os);
		paramMap.put("channel", channel);
		paramMap.put("httpver", "1");
		//paramMap.put("enver", request.getParameter("ENVER"));
//		LogManager.inforVersion(LOG, paramMap);
//		//http接口定义版本
		int v = ExtractVersion.getVersionNum(paramMap.get("httpver"));
		switch(v)
		{
		 	case 1://http接口版本1
		 		ShareBase shareBase = new ShareBase();
				shareBase.setId(paramMap.get("id"));
				shareBase.setMsg(paramMap.get("msg"));
				shareBase.setPakName(paramMap.get("pkgname"));
				shareBase.setOs(paramMap.get("os"));
				shareBase.setChnl(paramMap.get("channel"));
				return new PageManager().getAppInfor(shareBase);
		 	case 2:break;
		}
		return null;
	}
	
}
