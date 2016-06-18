package com.hummingbird.propagate.util;

import org.apache.commons.lang.StringUtils;

import com.hummingbird.common.exception.BusinessException;

	import java.security.MessageDigest;

	public class StringUtil
	{
	  public static String str;
	  public static final String EMPTY_STRING = "";
	  private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

		/**
		 *	银行卡号或者姓名 电话=部分展示
		 * @author YJY
		 * @since  2015年11月28日17:41:30
		 * @param T
		 *           
		 * @return String
		 * @throws BusinessException
		 */
		public String getShowString(String str) throws BusinessException {
			String ss = "";
	        if(org.apache.commons.lang.StringUtils.isNotBlank(str)){
	        	if(StringUtils.isNumeric(str)){//银行卡或者身份证
	        		if(str.length()>15){
	        			ss = str.substring(0, 4)+"******"+str.substring(str.length()-4);
	        		}else if(str.length()>2){
	        			ss = str.substring(0, 1)+"*"+str.substring(str.length()-1);
	        		}else if(str.length()>1){
	        			ss = "*"+str.substring(str.length()-1);
	        		}
	        	}else{//其他  姓名
	        		if(str.length()>2){
	        			ss = str.substring(0, 1)+"*"+str.substring(str.length()-1);
	        		}else if(str.length()>1){
	        			ss = "*"+str.substring(str.length()-1);
	        		}
	        	}
	        	
	        }
			return ss;
		}
	  
	  
	  private static String byteToHexString(byte b)
	  {
	    int n = b;
	    if (n < 0)
	      n = 256 + n;
	    int d1 = n / 16;
	    int d2 = n % 16;
	    return hexDigits[d1] + hexDigits[d2];
	  }

	  public static String byteArrayToHexString(byte[] b)
	  {
	    StringBuffer resultSb = new StringBuffer();
	    for (int i = 0; i < b.length; i++) {
	      resultSb.append(byteToHexString(b[i]));
	    }
	    return resultSb.toString();
	  }

	  public static String MD5Encode(String origin) {
	    String resultString = null;
	    try {
	      resultString = new String(origin);
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
	    } catch (Exception ex) {
	    }
	    return resultString;
	  }

	  public static boolean isEmpty(String str)
	  {
	    if ((str == null) || (str.trim().equalsIgnoreCase("")) || (str.trim().equalsIgnoreCase("null")))
	    {
	      return true;
	    }

	    return false;
	  }

	  public static boolean isNotEmpty(String str) {
	    if ((str != null) && (!str.trim().equalsIgnoreCase("null")) && (!str.trim().equalsIgnoreCase("")))
	    {
	      return true;
	    }

	    return false;
	  }

	  public static String generatePhoneCode()
	  {
	    String rand = String.valueOf(Math.random());
	    rand = rand.substring(2, 8);
	    return rand;
	  }
	}
