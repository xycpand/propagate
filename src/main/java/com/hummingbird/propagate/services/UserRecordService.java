package com.hummingbird.propagate.services;

import javax.servlet.http.HttpServletRequest;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.propagate.entity.ReadArticle;
import com.hummingbird.propagate.entity.ShareArticle;
import com.hummingbird.propagate.entity.WxUser;
import com.hummingbird.propagate.vo.SaveArticleVO;
import com.hummingbird.propagate.vo.SaveReadArticleVO;
import com.hummingbird.propagate.vo.SaveShareArticleVO;
import com.hummingbird.propagate.vo.SaveShareRecordVO;
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
	 * 加载propagate.js
	 * @param x_articleId
	 * @return
	 * @throws BusinessException
	 */
	public String loadPropagateJS(String x_articleId,HttpServletRequest request) throws BusinessException;
	
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
	 * 在微信中分享成功后，将分享记录保存传播数据库中
	 * @param vo
	 * @throws BusinessException
	 */
	public void saveShareRecord(SaveShareRecordVO vo) throws BusinessException;
	
	/**
	 * 上报微信消息
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public String saveUserInfo(UserVO userVO) throws BusinessException;
	
	

}
