/**
 * 
 */
package com.hummingbird.propagate.services.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.Md5Util;
import com.hummingbird.common.util.PropertiesUtil;
import com.hummingbird.commonbiz.vo.BaseUserToken;
import com.hummingbird.commonbiz.vo.UserToken;
import com.hummingbird.propagate.entity.Token;
import com.hummingbird.propagate.mapper.UserTokenMapper;
import com.hummingbird.propagate.services.TokenService;
import com.hummingbird.propagate.util.JedisPoolUtils;

import redis.clients.jedis.Jedis;

/**
 * @author huangjiej_2
 * 2014年10月18日 下午12:21:17
 */
@Service
public class TokenServiceImpl implements TokenService {

	protected  final Log log = LogFactory.getLog(getClass());
	@Autowired
	UserTokenMapper tokenmapper;
	
	/**
	 * 获取token，如果token超时会返回空
	 * @param token
	 * @return
	 */
	public Token getToken(String token){
		if(StringUtils.isBlank(token)){
			return null;
		}
		Token to = null;
		try {
			to = this.getTokenOnRedis(token);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("创建redis失败,可能redis配置文件未配置");
		}
		if(to == null){
			if (log.isDebugEnabled()) {
				log.debug(String.format("redis没有获取token数据,开始从数据库表查询!"));
			}
			to = tokenmapper.selectByTokenStr(token);
			if(to!=null&&!isOvertime(to)){
				saveTokenToRedis(to);
			}
			
		}
			
		if(to!=null)
			{
				if(isOvertime(to)){
					if (log.isDebugEnabled()) {
						log.debug(String.format("用户token已过期"));
					}
					this.removeTokenOnRedis(to.getToken());
					return null;
				}
				return to;
			}
		
		return null;
	}

	/**
	 * 检查是否超时
	 * @param to
	 * @return
	 */
	private boolean isOvertime(Token to) {
		long lastlogintime = to.getUpdateTime()!=null?to.getUpdateTime().getTime():to.getInsertTime().getTime();
		return to.getExpireIn()>0&&(long)to.getExpireIn()*1000+lastlogintime<System.currentTimeMillis();
	}
	
	/**
	 * 获取token，如果token超时会返回空
	 * @param token
	 * @return
	 */
	@Override
	public Token getToken(String token,String appId){
		if(StringUtils.isBlank(token)){
			return null;
		}
		Token to = null;
		try {
			to = this.getTokenOnRedis(token);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("创建redis失败,可能redis配置文件未配置");
		}
		if(to == null){
			if (log.isDebugEnabled()) {
				log.debug(String.format("redis没有获取token数据,开始从数据库表查询!"));
			}
			to = tokenmapper.selectByToken(new BaseUserToken(appId,null,token));
			if(to!=null&&!isOvertime(to)){
				saveTokenToRedis(to);
			}
			
		}
		
		if(to!=null)
		{
			if(isOvertime(to)){
				if (log.isDebugEnabled()) {
					log.debug(String.format("用户token已过期"));
				}
				this.removeTokenOnRedis(to.getToken());
				return null;
			}
			return to;
		}
		return null;
	}
	

	/* (non-Javadoc)
	 * @see com.pay2b.service.ITokenService#createToken(java.lang.String, java.lang.String)
	 */
	public UserToken createToken(String appId,int userId) {
		String token =new Md5Util().Encrypt(appId+userId+System.currentTimeMillis());
		Token record=new Token();
		record.setAppId(appId);
		record.setUserId(userId);
		record.setToken(token);
		record.setInsertTime(new Date());
		record.setUpdateTime(new Date());
		record.setExpireIn(getDefaultExpireIn());
		this.saveTokenToRedis(record);//
		tokenmapper.insert(record);
		return new BaseUserToken(appId,String.valueOf(userId),token,record.getExpireIn());
	}

	/* (non-Javadoc)
	 * @see com.pay2b.service.ITokenService#queryToken()
	 */
	@Override
	public UserToken queryToken(String appId,int userId) {
		Token selectByAppAndMobile = null;
	
		selectByAppAndMobile = tokenmapper.selectByToken(new BaseUserToken(appId, String.valueOf(userId), null));

		if(selectByAppAndMobile==null)
		{
			return null;
		}
		return new BaseUserToken(appId, String.valueOf(userId), selectByAppAndMobile.getToken(),selectByAppAndMobile.getExpireIn() );
	}
	
	/**
	 * 获取或创建token(如果token不存在,或token超时)
	 * @param appId
	 * @param userId
	 * @return
	 */
	@Override
	public UserToken getOrCreateToken(String appId,int userId){
		
		Token selectByAppAndMobile = tokenmapper.selectByToken(new BaseUserToken(appId, String.valueOf(userId), null));
		if(selectByAppAndMobile!=null&&isOvertime(selectByAppAndMobile)){
			if (log.isDebugEnabled()) {
				log.debug(String.format("token超时,更新token"));
			}
			tokenmapper.deleteByPrimaryKey(selectByAppAndMobile.getToken());
			selectByAppAndMobile=null;
		}
		if(selectByAppAndMobile==null)
		{
			return createToken(appId, userId);
		}else{
			this.saveTokenToRedis(selectByAppAndMobile);
		}
		return new BaseUserToken(appId,String.valueOf(userId),selectByAppAndMobile.getToken(),selectByAppAndMobile.getExpireIn());
		
	}
	
	/**
	 * 更新token,此方法已无用,被getOrCreateToken 代替
	 * @param selectToken
	 * @return 
	 */
	public UserToken RenewToken(UserToken token){
		int defaultExpireIn = getDefaultExpireIn();
		Token to = new Token();
		to.setExpireIn(defaultExpireIn);
		to.setToken(token.getToken());
		to.setUpdateTime(new Date());
		tokenmapper.updateByPrimaryKeySelective(to);
		((BaseUserToken)token).setExpirein(defaultExpireIn);
		return token;
	}
	
	private int getDefaultExpireIn(){
		//1小时有效
		return new PropertiesUtil().getInt("usertoken.expirein", 7*24*3600);//测试阶段暂时有效期改为一周     正式环境  再改回   2015年12月10日
	}
	
	/**
	 * 更新令牌的有效期
	 * @param token
	 */
	public void postponeToken(Token token){
		Token to = null;
		try {
			to = this.getTokenOnRedis(token.getToken());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			throw new NumberFormatException("创建redis失败,可能redis配置文件未配置");
		}
		if(to!= null){//从redis取数据  
			to.setUpdateTime(new Date());
			to.setExpireIn(this.getDefaultExpireIn());//延长有效期
			tokenmapper.updateByPrimaryKeySelective(to);
			updateTokenOnRedis(to);
		}else{
			to = tokenmapper.selectByTokenStr(token.getToken());
			if(to!=null){
				to.setUpdateTime(new Date());
				to.setExpireIn(getDefaultExpireIn());
				tokenmapper.updateByPrimaryKeySelective(to);
				saveTokenToRedis(to);
			}
		}
	}
	
	/**
	 * 从redis读取token
	 * @author YJY 
	 * @param token
	 * @since 2015-11-25 17:22:50
	 */
	public  Token getTokenOnRedis(String token) {
		Jedis jedis = JedisPoolUtils.getJedisIfNessary();
//		jedis.flushDB();
		if(jedis != null && jedis.exists(token)){
			String json;
			try {
				json = jedis.get(token);
			} 
			finally{
				JedisPoolUtils.returnRes(jedis);
			}
			
				Token to;
				try {
					to = JsonUtil.convertJson2Obj(json, Token.class);
//					System.out.println(to.getToken());
					if(to!=null)
					{
						return to;
					}
				} catch (DataInvalidException e) {
					log.error("转换失败",e);
				}
			

			//System.out.println(json);
		}
		return null;
	}
	
	/**
	 * 将token保存到redis
	 * @author YJY 
	 * @param token
	 * @since 2015-11-25 17:22:50
	 */
	public  Token saveTokenToRedis(Token record) {
		Jedis jpu = JedisPoolUtils.getJedisIfNessary();
		
		try {
			if(jpu!=null&&record!=null){
				try {
					if(!jpu.exists(record.getToken())){
						jpu.set(record.getToken(), JsonUtil.convert2Json(record));
						jpu.expire(record.getToken(), 3600);//设置有效期
					}	 
				} 
				finally{
					JedisPoolUtils.returnRes(jpu);
				}
				
			}
			
		} catch (DataInvalidException e) {
			log.error("转换失败",e);
		}
		return record;
	}
	
	/**
	 * 更新redis上的token
	 * @author YJY 
	 * @param token
	 * @since 2015-11-25 17:22:50
	 */
	public  Token updateTokenOnRedis(Token token) {
		Jedis jedis = JedisPoolUtils.getJedisIfNessary();
//		jedis.flushDB();
		if(jedis != null && token != null && jedis.exists(token.getToken())){
			String json;
			try {
				json = JsonUtil.convert2Json(token);
				try {
					jedis.set(token.getToken(), json);
					jedis.expire(token.getToken(), 3600);
				} 
				finally{
					JedisPoolUtils.returnRes(jedis);
				}
			} catch (DataInvalidException e) {
				log.error("转换失败",e);
			}
			
		
		}
		return null;
	}
	
	/**
	 * 将token从redis移除
	 * @author YJY 
	 * @param token
	 * @since 2015-11-25 17:22:50
	 */
	public  Long removeTokenOnRedis(String token) {
		
		Jedis jpu = JedisPoolUtils.getJedisIfNessary();
		Long ll = 0L;
		if(jpu!=null){
			try {
				ll= jpu.del(token);
			} 
			finally{
				JedisPoolUtils.returnRes(jpu);
			}
		}
		
		return ll;
	}
	/**
	 * 测试
	 * @author YJY
	 * @param test
	 */
	public static void main(String[] args) {
		Jedis jedis = JedisPoolUtils.getJedis();
//		jedis.flushDB();
		if(jedis.exists("11111")){
			String json = jedis.get("11111");
			
		

				Token aa;
				try {
					aa = JsonUtil.convertJson2Obj(json, Token.class);
					aa.getToken();
					System.out.println(aa.getAppId());
				} catch (DataInvalidException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

			System.out.println(json);
//			selectByAppAndMobile = tokenmapper.selectByToken(new BaseUserToken(appId, jedis.get(String.valueOf(userId)), null));
		}
	}

}
