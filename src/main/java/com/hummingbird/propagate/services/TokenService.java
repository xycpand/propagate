package com.hummingbird.propagate.services;

import com.hummingbird.commonbiz.vo.UserToken;
import com.hummingbird.propagate.entity.Token;

/**
 * 令牌服务service
 * @author huangjiej_2
 * 2014年10月18日 下午12:17:49
 */
public interface TokenService {

	/**
	 * 创建用户令牌，主要用于登录
	 * @param appId
	 * @param mobileNum
	 * @return
	 */
	public UserToken createToken(String appId,int userId);
	
	/**
	 * 查询用户令牌
	 * @return
	 */
	public UserToken queryToken(String appId,int userId);

	/**
	 * 获取token，如果token超时会返回空
	 * @param token
	 * @return
	 */
	public Token getToken(String token);

	/**
	 * 按appid和令牌查询数据
	 * @param token
	 * @param appId
	 * @return
	 */
	Token getToken(String token, String appId);

//	/**
//	 * 更新token
//	 * @param selectToken
//	 */
//	public UserToken RenewToken(UserToken token);
//
//	/**
//	 * 是否超时
//	 * @param to
//	 * @return
//	 */
//	public abstract boolean isOvertime(UserToken to);

	/**
	 * 获取或创建token(如果token不存在,或token超时)
	 * @param appId
	 * @param userId
	 * @return
	 */
	public abstract UserToken getOrCreateToken(String appId, int userId);

	/**
	 * 更新令牌的有效期
	 * @param token
	 */
	public void postponeToken(Token token);

	
	
}
