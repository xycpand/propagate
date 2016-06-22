package com.hummingbird.propagate.services;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.propagate.entity.Article;

/**
 * @author panda
 * 2016年6月15日 下午2:59:01
 */
public interface ArticleService {

	/**
	 * 根据id查询文章信息
	 * @param Integer id
	 * @return Article
	 * @throws BusinessException
	 */
	public Article selectArticleById(String id) throws BusinessException;
	

	/**
	 * 根据id检查文章信息是否存在
	 * @param Integer id
	 * @return Article
	 * @throws BusinessException
	 */
	public Article checkArticleById(String id) throws BusinessException;
	

}
