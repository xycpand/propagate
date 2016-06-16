package com.hummingbird.propagate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.commonbiz.exception.TokenException;
import com.hummingbird.propagate.entity.Token;
import com.hummingbird.propagate.entity.WxUser;
import com.hummingbird.propagate.mapper.WxUserMapper;
import com.hummingbird.propagate.services.TokenService;
import com.hummingbird.propagate.services.WxUserService;
@Service
public class WxUserServiceImpl implements WxUserService{

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	TokenService tokenSrv;
	@Autowired
	WxUserMapper wxUserDao;
	
	@Override
	public WxUser selectUserById(Integer userid) throws BusinessException {
		try{
			wxUserDao.selectByPrimaryKey(userid);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("通过id查询用户信息失败。");
		}
		return null;
	}

	
	/**
	 * 根据token获取openid
	 * @param tokenStr
	 * @param appId
	 * @return
	 * @throws TokenException
	 */
	public String getOpenidByUserId(String tokenStr,String appId)throws TokenException{
		Token token = tokenSrv.getToken(tokenStr, appId);
		if (token == null) {
			log.error(String.format("token[%s]验证失败,或已过期,请重新登录", tokenStr));
			throw new TokenException("token验证失败,或已过期,请重新登录");
		}
		WxUser user = wxUserDao.selectByPrimaryKey(token.getUserId());
		tokenSrv.postponeToken(token);
		return user.getOpenid();
	}


}
