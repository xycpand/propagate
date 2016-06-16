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
	 * 根据token获取openid
	 * @param tokenStr
	 * @param appId
	 * @return
	 * @throws TokenException
	 */
	public String getOpenidByUserId(String tokenStr,String appId)throws TokenException;
	

}
