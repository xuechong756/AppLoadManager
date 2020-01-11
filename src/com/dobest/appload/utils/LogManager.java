package com.dobest.appload.utils;

import java.util.Map;

import org.slf4j.Logger;

public class LogManager {
	
	private LogManager(){}
	
	//输出appid，appver，dever，systype， httpver，ENVER
	public static void inforVersion(Logger log, Map<String, String> map)
	{
		log.info("appid:" + map.get("appid"));//app包名
		
		log.info("appver:" + map.get("appver"));//客户端版本
		
		log.info("dever:" + map.get("dever"));//内部版本
		
		log.info("systype:" + map.get("systype"));//设备平台， 1表示android, 2表示ios
		log.info("httpver:" + map.get("httpver"));//http接口版本
		
		log.info("enver:" + map.get("enver"));//解码版本
	}

}
