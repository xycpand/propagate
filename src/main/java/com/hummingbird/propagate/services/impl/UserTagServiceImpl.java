package com.hummingbird.propagate.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.propagate.entity.Article;
import com.hummingbird.propagate.entity.ArticleTag;
import com.hummingbird.propagate.entity.Tag;
import com.hummingbird.propagate.entity.WxUser;
import com.hummingbird.propagate.mapper.ArticleMapper;
import com.hummingbird.propagate.mapper.ArticleTagMapper;
import com.hummingbird.propagate.mapper.TagMapper;
import com.hummingbird.propagate.mapper.UserTagMapper;
import com.hummingbird.propagate.mapper.WxUserMapper;
import com.hummingbird.propagate.services.UserTagService;
import com.hummingbird.propagate.vo.AddArticleTagBodyVO;
import com.hummingbird.propagate.vo.QueryUserTagReruenVO;
import com.hummingbird.propagate.vo.TagVO;
@Service
public class UserTagServiceImpl implements UserTagService {
	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
	
	@Autowired
	WxUserMapper wxUserDao;
	@Autowired
	UserTagMapper userTagDao;
	@Autowired
	TagMapper tagDao;
	@Autowired
	ArticleTagMapper articleTagDao;
	@Autowired
	ArticleMapper articleDao;
	
	@Override
	public QueryUserTagReruenVO queryUserTag(String openid) {
		//查询用户
		WxUser wxUser= getWxUser(openid);
		//查询用户标签表
		List<TagVO> tags=userTagDao.queryUserTag(wxUser.getUserid());
		QueryUserTagReruenVO result=new QueryUserTagReruenVO();
		result.setUserTags(tags);
		return result;
	}
	
	@Override
	public QueryUserTagReruenVO queryArticleTag(String articleId) {
		
		// 查询文章标签
		List<TagVO> tags=articleTagDao.queryArticleTag(articleId);
		QueryUserTagReruenVO result=new QueryUserTagReruenVO();
		result.setUserTags(tags);
		return result;
	}
	
	@Override
	public QueryUserTagReruenVO queryHotTag() {
		//查询使用最多的前五个
		List<TagVO> tags=tagDao.queryHotTag(5);
		QueryUserTagReruenVO result=new QueryUserTagReruenVO();
		result.setUserTags(tags);
		return result;
	}
	
	@Override
	public void addArticleTag(AddArticleTagBodyVO body) throws BusinessException{
		//查询用户
		WxUser wxUser= getWxUser(body.getOpenid());
		// 查询文章是否属于该用户
		Article article=articleDao.selectByPrimaryKey(body.getArticleId());
		ValidateUtil.assertNull(article, "文章信息记录未找到！");
		if(article.getUserid()!=wxUser.getUserid()){
			throw new BusinessException(245903,"该用户不是此文章作者，无法编辑！");
		}
		//查询文章标签
		List<TagVO> articletags=articleTagDao.queryArticleTag(body.getArticleId());
		for(String tagname:body.getTagName()){
			
			if(!articletags.contains(tagname)){
				Tag tag=new Tag();
				//判断该标签是否存在t_tag
				List<Tag> tags=tagDao.queryTagByName(tagname);
				if(tags.size()>0){
					//存在
					//更新t_tag热度表，use_num加1
					tag=tags.get(0);
					tag.setUseNum(tag.getUseNum()+1);
					tag.setUpdateTime(new Date());
					tag.setUpdateRemark("更新热度");
					tagDao.updateByPrimaryKey(tag);
				}else{
					//不存在
					//t_tag添加该标签
					tag.setTagName(tagname);
					tag.setInsertTime(new Date());
					tag.setStatus("OK#");
					tag.setUserid(wxUser.getUserid());
					tag.setIsrecommend("NO#");
					tag.setUpdateTime(new Date());
					tag.setUpdateRemark("插入标签");
					tagDao.insert(tag);
				}
				//为该文章添加该标签
				ArticleTag articleTag=new ArticleTag();
				articleTag.setArticleId(body.getArticleId());
				articleTag.setTagId(tag.getId());
				articleTag.setTagName(tagname);
				articleTagDao.insert(articleTag);
			}
		}
	}
	@Override
	public void delArticleTag(AddArticleTagBodyVO body) throws BusinessException {
		//查询用户
		WxUser wxUser= getWxUser(body.getOpenid());
		// 查询文章是否属于该用户
		Article article=articleDao.selectByPrimaryKey(body.getArticleId());
		ValidateUtil.assertNullnoappend(article, "文章信息记录未找到！");
		if(article.getUserid()!=wxUser.getUserid()){
			throw new BusinessException(245904,"该用户不是此文章作者，无法编辑！");
		}
		//查询文章标签
		List<TagVO> articletags=articleTagDao.queryArticleTag(body.getArticleId());
		for(String tagname:body.getTagName()){
			
			if(articletags.contains(tagname)){
				//删除该标签
				articleTagDao.deleteByName(body.getArticleId(), tagname);
			}
		}
		
	}
	
	public WxUser getWxUser(String openid){
		WxUser wxUser=wxUserDao.selectUserByOpendId(openid);
		if(wxUser==null){
			wxUser = new WxUser();
			wxUser.setOpenid(openid);
			wxUser.setInsertTime(new Date());
			wxUser.setUpdateTime(new Date());
			wxUserDao.insert(wxUser);
		}
		return wxUser;
	}

	

	

	
	
}
