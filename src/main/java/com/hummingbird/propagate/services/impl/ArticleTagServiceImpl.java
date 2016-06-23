package com.hummingbird.propagate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.propagate.entity.ArticleTag;
import com.hummingbird.propagate.mapper.ArticleTagMapper;
import com.hummingbird.propagate.services.ArticleTagService;
@Service
public class ArticleTagServiceImpl implements ArticleTagService{

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
	@Autowired
	ArticleTagMapper articleTagDao;
	
	@Override
	public List<ArticleTag> queryArticleTagByArticleId(String articleId) throws BusinessException {
		 List<ArticleTag>  tags = null;
		 try{
			 tags = articleTagDao.queryArticleTagByArticleId(articleId);
		 }catch(DataAccessException e){
			 e.printStackTrace();
			 log.debug("根据文章id查询文章标签失败。");
		 }
		return tags;
	}
	

}
