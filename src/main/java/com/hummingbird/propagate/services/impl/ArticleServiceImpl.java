package com.hummingbird.propagate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.commonbiz.exception.TokenException;
import com.hummingbird.propagate.entity.Article;
import com.hummingbird.propagate.entity.Token;
import com.hummingbird.propagate.entity.WxUser;
import com.hummingbird.propagate.mapper.ArticleMapper;
import com.hummingbird.propagate.mapper.WxUserMapper;
import com.hummingbird.propagate.services.ArticleService;
import com.hummingbird.propagate.services.TokenService;
@Service
public class ArticleServiceImpl implements ArticleService{

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	TokenService tokenSrv;
	@Autowired
	ArticleMapper articleDao;
	@Autowired
	WxUserMapper wxUserDao;
	
	@Override
	public Article selectArticleById(Long id) throws BusinessException {
		Article article = null;
		try{
			article = articleDao.selectByPrimaryKey(id);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("通过id查询文章信息失败。");
		}
		return article;
	}
	

	@Override
	public Article checkArticleById(Long id) throws BusinessException {
		Article article = null;
		try{
			article = articleDao.selectByPrimaryKey(id);
			if(article == null){
				throw new BusinessException("不存在id为【"+id+"】的文章信息。");
			}
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("通过id查询文章信息失败。");
		}
		return article;
	}

}
