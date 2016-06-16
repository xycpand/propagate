package com.hummingbird.propagate.services.impl;

import org.apache.log4j.chainsaw.Main;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.BusinessException;
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
		String jsonContent = "";
		try{
			Long contentId = vo.getContentId();
			
			//要获取的js脚本
			String jsScript = "<script language='javascript'>";
				   jsScript+= "alert('这是请求的内容。');";
			       jsScript+= "</script>";
			       
			 //转为json字符串
			// jsonContent = JsonUtil.convert2Json(jsScript);	
			 jsonContent = jsScript;
			 //Jsoup
			 
			UserRecord userRecord = new UserRecord();
			userRecord.setOpenid(vo.getOpenId());
			userRecord.setArticleId(contentId);
			//保存用户浏览记录
			userRecordDao.insert(userRecord);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("保存用户浏览记录失败。");
		}
		return jsonContent;
	}

  public  static void Main(){
	 // Jsoup.parse(url, timeoutMillis)
	  
  }

	@Override
	public void saveUserRecord(String appId, SaveUserRecordVO vo) throws BusinessException {
		try{
			//根据token获取openid
			String openid = wxUserService.getOpenidByUserId(vo.getToken(), appId);
			
			articleService.checkArticleById(vo.getArticleId());

			UserRecord userRecord = new UserRecord();
			userRecord.setOpenid(openid);
			userRecord.setArticleId(vo.getArticleId());
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
