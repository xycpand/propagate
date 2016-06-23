package com.hummingbird.propagate.services;

import java.util.List;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.propagate.entity.ArticleTag;

/**
 * 文章标签管理
 * @author panda
 *
 */
public interface ArticleTagService {

	/**
	 * 根据文章id查询文章标签列表
	 * @param articleId
	 * @return
	 * @throws BusinessException
	 */
	public List<ArticleTag> queryArticleTagByArticleId(String articleId)throws BusinessException;

	
}
