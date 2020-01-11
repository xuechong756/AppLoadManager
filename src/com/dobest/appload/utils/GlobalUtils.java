package com.dobest.appload.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
/**
 * 一些公用的全局函数
 * 
 * @author hqm
 * 
 */
public final class GlobalUtils {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalUtils.class);
	private static GlobalUtils obj = null;
	
	private GlobalUtils(){}
	/**
	 * 取得 WEB-INF 目录所在的路径(不包括 WEB-INF 目录自身)
	 */
	public static String getWebInfPath() {
		return getWebInfPath(obj);
	}

	/**
	 * 取得 WEB-INF 目录所在的路径(不包括 WEB-INF 目录自身) 注意： obj 对象必须是 WEB-INF
	 * 子目录下的类对象实例或传入null
	 */
	public static String getWebInfPath(Object obj) {
		if (obj == null) {
			if (GlobalUtils.obj == null)
				GlobalUtils.obj = new GlobalUtils();
			obj = GlobalUtils.obj;
		}
		String path = obj.getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		if (path.startsWith("file://"))
			path = path.substring("file:/".length());
		else if (path.startsWith("file:"))
			path = path.substring("file:".length()); // solaris下测试发现就只有一个斜杠
		try {
			path = java.net.URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("", e);
		}
		int iPos = path.indexOf("WEB-INF/");
		if (iPos != -1)
			path = path.substring(0, iPos);
		else
			path = "";
		return path;
	}
	
	//获取Tomcat 绝对路径
	public static String getTomcatPath()
	{
		String origina = getWebInfPath();
		if(origina == null)
			return null;
		String[] paths = origina.split("/");
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 1; i < paths.length - 2; i++)
		{
			stringBuilder.append(paths[i] + "/");
		}
		return stringBuilder.toString();
	}
	
	public static int SPEncrypt1_0_Decode(byte pInOut[], int iDataLen) {
		if (0 != (iDataLen & 0x3)) {
			return -1;
		}

		int iCount = (iDataLen >> 2) - 1;

		int pBuffInOut[] = new int[iCount + 1];

		for (int i = 0; i < iCount + 1; i++) {
			pBuffInOut[i] = toInt(pInOut[i * 4], pInOut[i * 4 + 1],
					pInOut[i * 4 + 2], pInOut[i * 4 + 3]);
		}

		int dwRand = pBuffInOut[iCount];
		for (int i = 0; i < iCount; ++i) {
			pBuffInOut[i] = pBuffInOut[i] ^ dwRand;
		}
		pBuffInOut[iCount] = 0;

		for (int i = 0; i < iCount + 1; i++) {
			pInOut[i * 4] = (byte) ((pBuffInOut[i] >> 24) & 0xff);
			pInOut[i * 4 + 1] = (byte) ((pBuffInOut[i] >> 16) & 0xff);
			pInOut[i * 4 + 2] = (byte) ((pBuffInOut[i] >> 8) & 0xff);
			pInOut[i * 4 + 3] = (byte) (pBuffInOut[i] & 0xff);
		}

		return 0;
	}

	public static byte[] decodeBASE64(String s) {
		if (s == null)
			return new byte[0];
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return decoder.decodeBuffer(s);
		} catch (Exception e) {
			LOG.error("", e);
		}
		return new byte[0];
	}

	final static String PARAMS_KEY = "ENCODE_DATA";//加密数据包
	final static String ENCODE_KEY = "ENVER";//加密版本号
	/*解析加密的URL参数*/
	public static Map<String, String> parseUrlParams(HttpServletRequest request) {
		Map<String, String> reqParams = new HashMap<String, String>();
		Map<?,?> map = request.getParameterMap();
		for (Object key : map.keySet()){
			if (!PARAMS_KEY.equals(key))
				reqParams.put((String) key, request.getParameter((String) key));
		}
		String params = request.getParameter(PARAMS_KEY);
	
		params = URLEncrypt.DecodeString(params, request.getParameter(ENCODE_KEY));
		try {
			if (params != null && !params.isEmpty()) {
				params = params.trim();
				if (params.indexOf("&") > -1) {
					String[] fieldArray = params.split("&");
					for (int i = 0; i < fieldArray.length; i++) {
						String key = null;
						String value = null;
						if (fieldArray[i].indexOf("=") > -1) {
							String[] _fieldArray = fieldArray[i].split("=");
							if (_fieldArray.length > 1) {
								key = URLDecoder.decode(_fieldArray[0], "UTF-8");
								value = URLDecoder.decode(_fieldArray[1], "UTF-8");
								if (key != null && key.length() > 0 && value != null && value.length() > 0)
									reqParams.put(key, value);
							}
						}
					}
				} 
			}
		} catch (Exception e) {
			LOG.error("", e);
		}
		return reqParams;
	}

	// byte数组转整形
	public static int toInt(byte b0, byte b1, byte b2, byte b3) {
		int iOutcome = 0;
		iOutcome = iOutcome | (b0 & 0xFF) << (8 * 3);
		iOutcome = iOutcome | (b1 & 0xFF) << (8 * 2);
		iOutcome = iOutcome | (b2 & 0xFF) << (8 * 1);
		iOutcome = iOutcome | (b3 & 0xFF) << (8 * 0);
		return iOutcome;
	}
}
