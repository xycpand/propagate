package com.hummingbird.propagate.services;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.commonbiz.exception.TokenException;
import com.hummingbird.propagate.entity.WxUser;

/**
 * @author panda
 * 2016年6月15日 下午2:59:01
 * 本类主要做为 用户浏览记录service
 */
public interface WxUserService {

	/**
	 * 根据id查询用户信息
	 * @param userid
	 * @return WxUser
	 * @throws BusinessException
	 */
	public WxUser selectUserById(Integer userid) throws BusinessException;
	

	/**
	 * 根据unionid查询用户信息
	 * @param unionid
	 * @return WxUser
	 * @throws BusinessException
	 */
	public WxUser selectUserByUnionid(String unionid) throws BusinessException;
	
	/**
	 * 根据openId查询用户id，不存在则创建
	 * @param openId
	 * @return
	 * @throws BusinessException
	 */
	public Integer selectUserIdByOpenId(String openId) throws BusinessException;
	
	/**
	 * 根据openid查询用户信息
	 * @param openid
	 * @return WxUser
	 * @throws BusinessException
	 */
	public WxUser selectUserByOpendId(String openid) throws BusinessException;

	
	public void updateByPrimaryKey(WxUser wxUser) throws BusinessException;
	

	public void addWxUserInfo(WxUser wxUser);
	
	/**
	 * 根据token获取openid
	 * @param tokenStr
	 * @param appId
	 * @return
	 * @throws TokenException
	 */
	public String getOpenidByUserId(String tokenStr,String appId)throws BusinessException, TokenException;
	

}
