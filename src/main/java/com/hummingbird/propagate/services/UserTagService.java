package com.hummingbird.propagate.services;

import java.util.List;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.propagate.entity.ArticleTag;
import com.hummingbird.propagate.entity.UserTag;
import com.hummingbird.propagate.vo.AddArticleTagBodyVO;
import com.hummingbird.propagate.vo.QueryUserTagReruenVO;

/**
 * 2016年6月22日 上午11点10分
 * @author Liudou
 *本类主要处理用户标签相关逻辑
 */
public interface UserTagService {

	public QueryUserTagReruenVO queryUserTag(String openid);

	public QueryUserTagReruenVO queryArticleTag( String articleId);

	public QueryUserTagReruenVO queryHotTag();

	public void addArticleTag(AddArticleTagBodyVO body) throws BusinessException;

	public void delArticleTag(AddArticleTagBodyVO body) throws BusinessException;
	
	public UserTag queryUserTagByUserIdAndTagId(Integer userId,Integer tagId) throws BusinessException;
	
	public void addUserTag(UserTag userTag) throws BusinessException;
	
	public void updateUserTag(UserTag userTag) throws BusinessException;
	
	/**
	 * 更新用户标签信息
	 * @param articleId
	 * @param userid
	 * @throws BusinessException
	 */
	public void  saveUserTag(String way, List<ArticleTag> tags, Integer userid) 
			throws BusinessException;
}
