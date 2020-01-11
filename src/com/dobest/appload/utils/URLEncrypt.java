package com.dobest.appload.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**解码编码
 * @author demonhqm
 *
 */
public class URLEncrypt {
	
	private static final Logger LOG = LoggerFactory.getLogger(URLEncrypt.class);
	// 加密
	public static String EncodeString(String sSource) {
		if (sSource == null || sSource.length() == 0)
			return sSource;
		byte pIn[] =  sSource.getBytes();
		//先异或编�?
		byte pOut[] = SPEncrypt1_0_Encrypt1(pIn, pIn.length);	
		// 先base64编码
		String res = encodeBASE64(pOut);

		return res;
	}	

	// 解密
	public static String DecodeString(String sSource, String decodeId) {
		if (sSource == null || sSource.length() == 0)
			return sSource;
		
		switch(ExtractVersion.getVersionNum(decodeId))
		{
		case 1:
			//解决加号被转换成空格
			String s  = sSource.replace(" ", "+"); 
			
			// 先base64解码
			byte inOut[] = decodeBASE64(s);
			// 再异或解�?
			SPEncrypt1_0_Decode(inOut, inOut.length);
			
			String res = null;
			try {
				res =  new String(inOut,"UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
				LOG.error("", e);
			}
			return res;
			default:return null;
		}
	
	}

	// byte数组转整�?
	public static int toInt(byte b0, byte b1, byte b2, byte b3) {
		int iOutcome = 0;
		iOutcome = iOutcome | (b0 & 0xFF) << (8 * 3);
		iOutcome = iOutcome | (b1 & 0xFF) << (8 * 2);
		iOutcome = iOutcome | (b2 & 0xFF) << (8 * 1);
		iOutcome = iOutcome | (b3 & 0xFF) << (8 * 0);
		return iOutcome;
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
	
	public static byte[] SPEncrypt1_0_Encrypt1(byte pIn[], int iInLen)
	{
		int residual = iInLen & 0x3;
		int iCount = iInLen >> 2;

		int dwRand = (new Random()).nextInt();
		int pBuffOut[] = new int[iCount + 2]; //加的长度�?个四字节为加的随机数�?个四字节为补齐除不尽4的部�?
		int pBuffIn[] = new int[iCount];
		
		int iOutLen = 0;
		
		for (int i = 0; i < iCount; i++) {
			pBuffIn[i] = pIn[i * 4] + (pIn[i *4 + 1] << 8 & 0x0000ff00)  
					+ (pIn[i *4 + 2] << 16 & 0x00ff0000) 
					+ (pIn[i *4 + 3] << 24 & 0xff000000);  
		}
		
		for (int i = 0; i < iCount; ++i)
		{
			pBuffOut[i] = pBuffIn[i] ^ dwRand;
		}
		
		byte pOut[] = null;
		
		if (0 == residual)
		{
			pBuffOut[iCount] = dwRand;
			iOutLen = iInLen + 4;
			
			pOut =  new byte[iOutLen];
			
			for (int i = 0; i < iCount + 1; i++) {
				pOut[i * 4] = (byte) ((pBuffOut[i] >> 24) & 0xff);
				pOut[i * 4 + 1] = (byte) ((pBuffOut[i] >> 16) & 0xff);
				pOut[i * 4 + 2] = (byte) ((pBuffOut[i] >> 8) & 0xff);
				pOut[i * 4 + 3] = (byte) (pBuffOut[i] & 0xff);
			}
		}
		else
		{
			pBuffOut[iCount] = 0;
			
			for(int j = 0; j < residual; ++j){
				pBuffOut[iCount] += (pIn[iCount * 4 + j] ) << (8 *j);
			}
			
			pBuffOut[iCount] = pBuffOut[iCount] ^ dwRand;
			pBuffOut[iCount + 1] = dwRand;
			iOutLen = (iCount + 2) << 2;
			
			pOut = new byte[iOutLen];
			
			for (int i = 0; i < iCount + 2; i++) {
				pOut[i * 4 + 3] = (byte) ((pBuffOut[i] >> 24) & 0xff);
				pOut[i * 4 + 2] = (byte) ((pBuffOut[i] >> 16) & 0xff);
				pOut[i * 4 + 1] = (byte) ((pBuffOut[i] >> 8) & 0xff);
				pOut[i * 4] = (byte) (pBuffOut[i] & 0xff);
			}
		}

		
		return pOut;
	}
	

	public static byte[] decodeBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return b;
		} catch (Exception e) {
			LOG.error("", e);
			return null;
		}
	}
	

	public static String encodeBASE64(byte[] code) {
		if (code == null)
			return null;
		BASE64Encoder encode = new BASE64Encoder();
		try {
			String s = encode.encodeBuffer(code);
			return s;
		} catch (Exception e) {
			LOG.error("", e);
			return null;
		}
	}

}