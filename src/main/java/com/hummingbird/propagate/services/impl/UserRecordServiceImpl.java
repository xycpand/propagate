package com.hummingbird.propagate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.commonbiz.exception.TokenException;
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
