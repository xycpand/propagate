package com.hummingbird.propagate.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.PropertiesUtil;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.propagate.entity.Token;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

public class JedisPoolUtils {
	static PropertiesUtil pu = new PropertiesUtil();
	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(JedisPoolUtils.class);
	private static JedisPool jedisPool;

	/**
	 * 建立连接池 真实环境，一般把配置参数缺抽取出来。
	 * 
	 */
	private static void createJedisPool() {
		if (log.isDebugEnabled()) {
			log.debug("创建redis连接中");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		// 建立连接池配置参数
	/*	config.setMaxActive(1000);
		config.setMaxIdle(10000000);
		config.setMaxWait(10000);
		jedisPool = new JedisPool(config, "127.0.0.1", 6379, 10000, "paas2009");*/
		System.out.println(jedisPool);
		String maxActivity = pu.getProperty("maxActive");
		String maxWait = pu.getProperty("maxWait");
		String maxIdle = pu.getProperty("maxIdle");
		String port = pu.getProperty("redisPort");
		String redisUrl = pu.getProperty("redisUrl");
		String timeout = pu.getProperty("timeout");
		String password = pu.getProperty("password");
		Integer timeOut = Integer.parseInt(timeout);
		if (StringUtils.isNotBlank(maxActivity)) {
			Integer maxactivity = Integer.parseInt(maxActivity);
			if (maxactivity != null && maxactivity >= 0)
				config.setMaxActive(maxactivity);
		}
		// 设置最大阻塞时间，记住是毫秒数milliseconds
		if (StringUtils.isNotBlank(maxWait)) {
			Integer maxwait = Integer.parseInt(maxWait);
			if (maxwait != null && maxwait >= 0)
				config.setMaxWait(maxwait);
		}

		// 设置空间连接
		if (StringUtils.isNotBlank(maxIdle)) {
			Integer maxidle = Integer.parseInt(maxIdle);
			if (maxidle != null && maxidle >= 0)
				config.setMaxIdle(maxidle);
		}
		Integer porT =  null;
		if (port != null) {
			porT = Integer.parseInt(port);
		}else{
			log.error("端口号未设置");
			return;
		}
		if (timeOut == null) {
			timeOut = 10000;
			
		}
		try {
			// 创建连接池
			jedisPool = new JedisPool(config, redisUrl, porT, timeOut, password);
			if (log.isDebugEnabled()) {
				log.debug("redis连接池创建成功" + redisUrl + porT + timeOut + password);
			}
		} catch (Exception e) {
			log.error("redis连接池创建失败",e);
		}

	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null)
			createJedisPool();
	}

	/**
	 * 获取一个jedis 对象,如果初始化失败(连接不成功),则会抛出异常
	 * 
	 * @return
	 */
	public static Jedis getJedis() throws JedisException {

		Jedis jedis = null;
		if (jedisPool == null) {
			poolInit();
		}
		return jedisPool.getResource();
	}
	
	/**
	 * 尝试获取jedis 如果jedis连接失败,则返回空,
	 * 适用于redis非必要的场合
	 * @return
	 */
	public static Jedis getJedisIfNessary(){
		try {
			return getJedis();
		} catch (JedisException e) {
			log.error("无法获取redis连接",e);
			return null;
		}
	}

	/**
	 * 归还一个连接
	 * 
	 * @param jedis
	 */
	public static void returnRes(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}

	/**
	 * 从redis读取数据
	 * @author YJY 
	 * @param key
	 * @param record
	 * @return 
	 * @since 2015-11-25 17:22:50
	 */
	public static  <T> T getDataOnRedis(String key,Class<T> type) throws Exception {
		Jedis jedis = JedisPoolUtils.getJedisIfNessary();
//		jedis.flushDB();
		if(jedis != null && jedis.exists(key)){
			String json = jedis.get(key);
				T to;
				try {
					to = (T) JsonUtil.convertJson2Obj(json, type);
//					System.out.println(to.getToken());
					if(to!=null)
					{
						if (log.isDebugEnabled()) {
							log.debug(String.format("从redis读取数据成功！"));
						}
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
	 * 将数据保存到redis(更新或者新增)
	 * @author YJY 
	 * @param key
	 * @param record
	 * @since 2015-11-25 17:22:50
	 */
	public static  <T> T saveDataToRedis(String key,T record) {
		Jedis jpu = JedisPoolUtils.getJedisIfNessary();
		
		try {
			if(jpu!=null&&record!=null){
				String result = jpu.set(key, JsonUtil.convert2Json(record));
				jpu.expire(key, getDefaultExpireIn());
//				jpu.expire(key, 7*24*3600);
				if (log.isDebugEnabled()) {
					if("OK".equalsIgnoreCase(result)){
						log.debug(String.format("保存数据到redis成功！"));
					}
					
				}
			}
			
		} catch (DataInvalidException e) {
			log.error("转换失败",e);
		}
		return record;
	}
	
	
	/**
	 * 将数据从redis移除
	 * @author YJY 
	 * @param key
	 * @since 2015-11-25 17:22:50
	 */
	public static  Long removeDataOnRedis(String key) {
		
		Jedis jpu = JedisPoolUtils.getJedisIfNessary();
		Long i = 0L;
		if(jpu!=null){
		 i= jpu.del(key);
		 if(log.isDebugEnabled()){
			 if(i>0){
					log.debug(String.format("数据从redis移除成功！"));
				}
		 }
		 
		}
		
		return i;
	}
	
	/**
	 * 执行redis,使用模板模式,需要用户创建执行器进行处理,如果获取不到jedis连接,会抛出JedisException异常
	 * @param executor
	 * @param onerror
	 * @return
	 * @throws BusinessException 业务操作
	 */
	public static Object execute(JedisExecutor executor) throws BusinessException{
		return execute(executor,null);
	}

	
	/**
	 * 执行redis,使用模板模式,需要用户创建执行器进行处理
	 * @param executor
	 * @param onerror
	 * @return
	 * @throws BusinessException 业务操作
	 */
	public static Object execute(JedisExecutor executor,JedisErrorExecutor onerror) throws BusinessException{
		Jedis jedis=null;
		ValidateUtil.assertNullnoappend(executor, "业务处理代码为空");
		try {
				jedis = getJedis();
				return executor.execute(jedis);//执行业务
			} catch (JedisException e) {
				if(onerror!=null){
					//由用户自定义处理器处理
					return onerror.onJedisGetError(e,jedis);     
				}
				else{
					//抛出错误 
					throw e;
				}
			
			} finally{ 
		        // 正确释放资源
		         if(jedis != null ) {
		                try {
							returnRes(jedis);
						} catch (Exception e) {
							log.error(String.format("归还redis连接出错"),e);
						}
		         }
			}
	}
	
	

	private static  int getDefaultExpireIn(){
		//1小时有效
		return new PropertiesUtil().getInt("usertoken.expirein", 3600);
	}
	
	public static void main(String[] args) {
		Jedis jpu = JedisPoolUtils.getJedis();	
//		jpu.flushDB();
		Token record=new Token();		
		record.setAppId("paas");		
		record.setUserId(46);		
		record.setToken("11111");		
		record.setInsertTime(new Date());		
		record.setUpdateTime(new Date());		
		record.setExpireIn(3600);		
 		String json;
		try {
			json = JsonUtil.convert2Json(record);
			jpu.set("11111", json);		
		} catch (DataInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 		
		try {
			Token mm=getDataOnRedis("11111", Token.class);
			System.out.println(mm.getToken()+"<<<"+mm.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Long i = removeDataOnRedis("11111");
//		 mm  = getDataOnRedis("11111", record.getClass());
		System.out.println(jpu);

		}
	
	/**
	 * @author john huang
	 * 2015年12月20日 下午5:04:38
	 * 本类主要做为 jedis错误 时的处理器
	 */
	public static interface JedisErrorExecutor{
		/**
		 * 获取不到jedis时,或jedis出错时的处理
		 * @param jedis 
		 * @return
		 */
		public Object onJedisGetError(JedisException e, Jedis jedis) throws BusinessException;
	}
	
	/**
	 * @author john huang
	 * 2015年12月20日 下午5:05:01
	 * 本类主要做为 jedis的执行器
	 */
	public static interface JedisExecutor{
		/**
		 * 执行jedis的内容
		 * @param jedis
		 * @return
		 */
		public Object execute(Jedis jedis) throws BusinessException;
		
	}
	
	
}
