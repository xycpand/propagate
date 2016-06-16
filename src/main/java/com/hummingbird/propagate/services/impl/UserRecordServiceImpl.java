package com.hummingbird.propagate.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.commonbiz.exception.TokenException;
import com.hummingbird.propagate.entity.AskByJS;
import com.hummingbird.propagate.entity.Token;
import com.hummingbird.propagate.entity.UserRecord;
import com.hummingbird.propagate.mapper.UserRecordMapper;
import com.hummingbird.propagate.services.ArticleService;
import com.hummingbird.propagate.services.TokenService;
import com.hummingbird.propagate.services.UserRecordService;
import com.hummingbird.propagate.services.WxUserService;
import com.hummingbird.propagate.vo.SaveUserRecordVO;

import net.sf.json.JSONObject;

@Service
public class UserRecordServiceImpl implements UserRecordService{

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	TokenService tokenSrv;
	@Autowired
	UserRecordMapper userRecordDao;
	@Autowired
	WxUserService wxUserService;
	@Autowired
	ArticleService articleService;
	


	@Override
	public String askByJS(AskByJS vo) throws BusinessException {
		String jsScript = "";
		try{
			Long contentId = vo.getContentId();
			
			//要获取的js脚本
			 jsScript = "<script language='javascript'>";
				   jsScript+= "alert('这是请求的内容。');";
			       jsScript+= "</script>";
			       
			 //转为json字符串
			  //jsScript = JsonUtil.convert2Json(jsScript);	
			 
			UserRecord userRecord = new UserRecord();
			userRecord.setOpenId(vo.getOpenId());
			userRecord.setContentId(contentId);
			//保存用户浏览记录
			userRecordDao.insert(userRecord);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("保存用户浏览记录失败。");
		}
		return jsScript;
	}

	/**
	 * 测试Jsoup
	 * @param args
	 */
	 public  static void main(String[] args){
			Document doc = null;
			 try {
				 //从一个URL加载一个Document
				//doc = Jsoup.connect("http://example.com/").get();
				//System.out.println(doc.toString());
				 
				// String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + IP);
				 String str = getJsonContent("http://baidu.com");
				 System.out.println(str);
				
				 //JSONObject obj = JSONObject.fromObject(str);
				
				//Connection 接口还提供一个方法链来解决特殊请求
				doc = Jsoup.connect("http://baidu.com")
						  .data("query", "Java")
						  .userAgent("Mozilla")
						  .cookie("auth", "token")
						  .timeout(3000)
						  .post();
				System.out.println(doc.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}	
			 
	  }

	  public static String getJsonContent(String urlStr)
	  {
	    try
	    {
	      URL url = new URL(urlStr);
	      HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();

	      httpConn.setConnectTimeout(3000);
	      httpConn.setDoInput(true);
	      httpConn.setRequestMethod("GET");

	      int respCode = httpConn.getResponseCode();
	      if (respCode == 200)
	      {
	        return ConvertStream2Json(httpConn.getInputStream());
	      }
	    }
	    catch (MalformedURLException e)
	    {
	      e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    return "";
	  }
	  
	  private static String ConvertStream2Json(InputStream inputStream)
	  {
	    String jsonStr = "";

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int len = 0;
	    try
	    {
	      while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
	      {
	        out.write(buffer, 0, len);
	      }

	      jsonStr = new String(out.toByteArray());
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    return jsonStr;
	  }
	 
	 
	@Override
	public void saveUserRecord(String appId, SaveUserRecordVO vo) throws BusinessException {
		try{
			//根据token获取openid
			String openid = wxUserService.getOpenidByUserId(vo.getToken(), appId);
			
			articleService.checkArticleById(vo.getArticleId());

			UserRecord userRecord = new UserRecord();
			userRecord.setOpenId(openid);
			userRecord.setContentId(vo.getArticleId());
			//保存用户浏览记录
			userRecordDao.insert(userRecord);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("保存用户浏览记录失败。");
		}
	}

	private Token getToken(String tokenStr,String appId)throws TokenException{
		Token token = tokenSrv.getToken(tokenStr, appId);
		if (token == null) {
			log.error(String.format("token[%s]验证失败,或已过期,请重新登录", tokenStr));
			throw new TokenException("token验证失败,或已过期,请重新登录");
		}
		return token;
	}
	

}
