package com.hummingbird.propagate.services;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.propagate.entity.ReadArticle;
import com.hummingbird.propagate.entity.ShareArticle;
import com.hummingbird.propagate.entity.WxUser;
import com.hummingbird.propagate.vo.SaveArticleVO;
import com.hummingbird.propagate.vo.SaveReadArticleVO;
import com.hummingbird.propagate.vo.SaveShareArticleVO;
import com.hummingbird.propagate.vo.SaveUserInfoVO;
import com.hummingbird.propagate.vo.UserVO;

/**
 * @author panda
 * 2016年6月15日 下午2:59:01
 * 本类主要做为 用户浏览记录service
 */
@SuppressWarnings("unused")
public interface UserRecordService {

	
	/**
	 * 保存文章阅读记录
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public String saveReadArticleRecord(SaveReadArticleVO vo) throws BusinessException;
	
	/**
	 * 保存文章分享记录
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public String saveShareArticleRecord(SaveShareArticleVO vo) throws BusinessException;
	
	/**
	 * 保存文章信息
	 * @param vo
	 * @throws BusinessException
	 */
	public void saveArticle(SaveArticleVO vo) throws BusinessException;
	
	/**
	 * 上报微信消息
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public String saveUserInfo(WxUser wxUser) throws BusinessException;
	
	/**
	 * 上报微信消息
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public String saveUserInfo(UserVO userVO) throws BusinessException;
	
	

}
