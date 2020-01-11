package com.dobest.appload.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class StringManager {
	
	private static final Logger LOG = LoggerFactory.getLogger(StringManager.class);
	private StringManager(){}
	
	// 提取字符串中的数字
	public static String[] getNumberFromString(String origine) {

		if(origine == null)
			return new String[0];
		Pattern p = Pattern.compile("[^0-9]");
		Matcher m = p.matcher(origine);
		String result = m.replaceAll("_");//不是正则限定内的字符用 _ 代替
		return result.split("_");
	}

	 // 将汉字转换为全拼  
  public static String StringToPinyin(String src) {  

	  char[] t1 = src.toCharArray();  
      String[] t2 = new String[t1.length];  
      HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
        
      t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
      t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
      t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
      StringBuilder stringBuilder = new StringBuilder();
      int t0 = t1.length;  
      try {  
          for (int i = 0; i < t0; i++) {  
              // 判断是否为汉字字符  
              if (java.lang.Character.toString(t1[i]).matches(  
                      "[\\u4E00-\\u9FA5]+")) {  
                  t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);  
                  stringBuilder.append(t2[0]);  
              } else  
            	  stringBuilder.append(java.lang.Character.toString(t1[i]));  
          }  
          return stringBuilder.toString();  
      } catch (BadHanyuPinyinOutputFormatCombination e1) {  
    	  LOG.error("", e1);
      }  
      return stringBuilder.toString();  
  }  
  
  //若为null或""返回true。否则返回false
  public static boolean isBlank(String str)
  {
	  if((str == null) || ("".equals(str)))
		  return true;
	  return false;
  }
  
  //将string从编码code1转成code1编码
  public static String changeToCoding(String string, String code1, String code2)
  {
	  try {
		return new String(string.getBytes(code1), code2);
	} catch (UnsupportedEncodingException e) {
		LOG.error("", e);
	}
	  return null;
  }

	//去掉指定重复字符串
	public static String removeSameString(String orgine, String same)
	{
		StringBuilder stringBuilder = new StringBuilder(orgine);
		int start = stringBuilder.indexOf(same);
		for(int find = stringBuilder.indexOf(same,start + 1); find != -1; find = stringBuilder.indexOf(same,start + 1))
		{
			stringBuilder.replace(find, find + same.length(), "");
		}
		return stringBuilder.toString();
	}	
	
	
	

}
