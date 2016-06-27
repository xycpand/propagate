package com.hummingbird.propagate.services.impl;

import java.util.Date;

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
		WxUser user = null;
		try{
		     user = wxUserDao.selectByPrimaryKey(userid);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("通过id查询用户信息失败。");
		}
		return user;
	}
	
	@Override
	public void addWxUserInfo(WxUser wxUser){
		try{
			wxUser.setInsertTime(new Date());
			wxUser.setUpdateTime(new Date());
		    wxUserDao.insert(wxUser);
		}catch(DataAccessException e){
			e.printStackTrace();
			log.error("保存微信用户信息失败。");
		}
	}

	
	/**
	 * 根据token获取openid
	 * @param tokenStr
	 * @param appId
	 * @return
	 * @throws TokenException
	 */
	public String getOpenidByUserId(String tokenStr,String appId)throws BusinessException,TokenException{
		Token token = tokenSrv.getToken(tokenStr, appId);
		if (token == null) {
			log.error(String.format("token[%s]验证失败,或已过期,请重新登录", tokenStr));
			throw new TokenException("token验证失败,或已过期,请重新登录");
		}
		WxUser user = wxUserDao.selectByPrimaryKey(token.getUserId());
		tokenSrv.postponeToken(token);
		if (user == null) {
			log.error(String.format("用户[%s]或未绑定微信", token.getUserId()));
			throw new BusinessException("用户不存在或未绑定微信");
		}
		return user.getOpenid();
	}

	@Override
	public WxUser selectUserByUnionid(String unionid) throws BusinessException {
		WxUser user = null;
		try{
			user = wxUserDao.selectUserByUnionid(unionid);
		}catch(DataAccessException e){
			throw new BusinessException("通过unionid查询微信用户信息失败。");
		}
		return user;
	}
	

	@Override
	public Integer selectUserIdByOpenId(String openId) throws BusinessException {
		WxUser user = null;
		try{
			user = wxUserDao.selectUserByOpendId(openId);
			if(user == null){
				user = new WxUser();
				user.setOpenid(openId);
				//不存在则添加
				addWxUserInfo(user);
			}
		}catch(DataAccessException e){
			throw new BusinessException("通过openId查询微信用户信息失败。");
		}
		return user.getUserid();
	}
	
	@Override
	public WxUser  checkUserByOpendId(String openId) throws BusinessException {
		WxUser user = null;
		try{
			user = wxUserDao.selectUserByOpendId(openId);
			if(user == null){
				user = new WxUser();
				user.setOpenid(openId);
				//不存在则添加
				addWxUserInfo(user);
			}
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("通过openId查询微信用户信息失败。");
		}
		return user;
	}

	@Override
	public WxUser  selectUserByOpendId(String openId) throws BusinessException {
		WxUser user = null;
		try{
			user = wxUserDao.selectUserByOpendId(openId);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("通过openId查询微信用户信息失败。");
		}
		return user;
	}

	@Override
	public void updateByPrimaryKeySelective(WxUser wxUser) throws BusinessException {
		try{
			wxUser.setUpdateTime(new Date());
		   wxUserDao.updateByPrimaryKeySelective(wxUser);
		}catch(DataAccessException e){
			throw new BusinessException("通过userid更新微信用户信息失败。");
		}
	}


}
