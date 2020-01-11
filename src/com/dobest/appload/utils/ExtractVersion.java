package com.dobest.appload.utils;

public class ExtractVersion {
	
	//获取http接口版本
	public static int getVersionNum(String v)
	{
		String[] str = StringManager.getNumberFromString(v);
		if(str.length != 1)
			return -1;
		return Integer.parseInt(str[0]);
	}
	
	//获取客户端加密版本
	public static int getEncryptVersion(String v)
	{
		return ExtractVersion.getVersionNum(v);
	}

}
