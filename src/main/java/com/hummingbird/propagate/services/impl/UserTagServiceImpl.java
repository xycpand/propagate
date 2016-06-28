package com.hummingbird.propagate.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.propagate.entity.Article;
import com.hummingbird.propagate.entity.ArticleTag;
import com.hummingbird.propagate.entity.Tag;
import com.hummingbird.propagate.entity.UserTag;
import com.hummingbird.propagate.entity.WxUser;
import com.hummingbird.propagate.mapper.ArticleMapper;
import com.hummingbird.propagate.mapper.ArticleTagMapper;
import com.hummingbird.propagate.mapper.TagMapper;
import com.hummingbird.propagate.mapper.UserTagMapper;
import com.hummingbird.propagate.mapper.WxUserMapper;
import com.hummingbird.propagate.services.ArticleTagService;
import com.hummingbird.propagate.services.UserTagService;
import com.hummingbird.propagate.vo.AddArticleTagBodyVO;
import com.hummingbird.propagate.vo.QueryUserTagReruenVO;
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
	ArticleTagService articleTagService;
	@Autowired
	ArticleMapper articleDao;
	
	@Override
	public QueryUserTagReruenVO queryUserTag(String openid) {
		//查询用户
		WxUser wxUser= getWxUser(openid);
		//查询用户标签表
		List<String> tags=userTagDao.queryUserTag(wxUser.getUserid());
		QueryUserTagReruenVO result=new QueryUserTagReruenVO();
		result.setTagNames(tags);
		return result;
	}
	
	@Override
	public QueryUserTagReruenVO queryArticleTag(String articleId) {
		
		// 查询文章标签
		List<String> tags=articleTagDao.queryArticleTag(articleId);
		QueryUserTagReruenVO result=new QueryUserTagReruenVO();
		result.setTagNames(tags);
		return result;
	}
	
	@Override
	public QueryUserTagReruenVO queryHotTag() {
		//查询使用最多的前五个
		List<String> tags=tagDao.queryHotTag(5);
		QueryUserTagReruenVO result=new QueryUserTagReruenVO();
		result.setTagNames(tags);
		return result;
	}
	
	@Override
	public void addArticleTag(AddArticleTagBodyVO body) throws BusinessException{
		//查询用户
		WxUser wxUser= getWxUser(body.getOpenid());
		// 查询文章是否属于该用户
		Article article=articleDao.selectByPrimaryKey(body.getArticleId());
		ValidateUtil.assertNull(article, "文章信息记录未找到！");
		ValidateUtil.assertNotEqual(article.getUserid(), wxUser.getUserid(), "该用户不是此文章作者，无法编辑！");
		//查询文章标签
		List<String> articletags=articleTagDao.queryArticleTag(body.getArticleId());
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
					tag.setUseNum(1);
					tagDao.insert(tag);
					//为用户添加该标签
					UserTag userTag=new UserTag();
					
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
	
	public void addUserTag(Integer userId ,String name){
		//查询用户是否有该标签
		List<UserTag> tags=userTagDao.queryUserTagByName(name);
		UserTag userTag=new UserTag();
		if(tags.size()>0){
			//有,使用次数加1
			userTag=tags.get(0);
			userTag.setCreatNum(userTag.getCreatNum());
			userTag.setUpdateTime(new Date());
			userTagDao.updateByPrimaryKey(userTag);
		}else{
			//没有，新增
			userTag.setUserid(userId);
			userTag.setInsertTime(new Date());
			userTag.setCreatNum(1);
			userTag.setReadNum(0);
			userTag.setShareNum(0);
			userTag.setUpdateTime(new Date());
			userTagDao.insert(userTag);
		}
	}
	
	@Override
	public void delArticleTag(AddArticleTagBodyVO body) throws BusinessException {
		//查询用户
		WxUser wxUser= getWxUser(body.getOpenid());
		// 查询文章是否属于该用户
		Article article=articleDao.selectByPrimaryKey(body.getArticleId());
		ValidateUtil.assertNullnoappend(article, "文章信息记录未找到！");
		ValidateUtil.assertNotEqual(article.getUserid(), wxUser.getUserid(), "该用户不是此文章作者，无法编辑！");
		//查询文章标签
		List<String> articletags=articleTagDao.queryArticleTag(body.getArticleId());
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

	@Override
	public UserTag queryUserTagByUserIdAndTagId(Integer userid, Integer tagId) throws BusinessException {
		UserTag userTag = new UserTag();
		try{
			userTag = userTagDao.queryUserTagByUserIdAndTagId(userid,tagId);
		}catch(DataAccessException e){
			e.printStackTrace();
			log.debug("根据用户id和标签id查询用户标签失败。");
			throw new BusinessException("根据用户id和标签id查询用户标签失败。");
		}
		return userTag;
	}

	@Override
	public void addUserTag(UserTag userTag) throws BusinessException {
		try{
			userTagDao.insert(userTag);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("添加用户标签失败。");
		}
	}

	@Override
	public void updateUserTag(UserTag userTag) throws BusinessException {
		try{
			userTagDao.updateByPrimaryKey(userTag);
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("更新用户标签失败。");
		}
	}
	
	@Override
	public void saveUserTag(String way,List<ArticleTag> tags, Integer userid) throws BusinessException {
		 if(CollectionUtils.isNotEmpty(tags)){
			 int count = 0;
			 for(ArticleTag tag : tags){
				 UserTag userTag = queryUserTagByUserIdAndTagId(userid,tag.getId());
			     if(userTag == null){
			    	 userTag = new UserTag();
			    	 userTag.setUserid(userid);
			    	 userTag.setTagId(tag.getId());
			    	 if("read".equals(way)){
			    		 userTag.setReadNum(1);
			    	 }else if("share".equals(way)){
			    		 userTag.setShareNum(1);
			    	 }else{//create
			    		 userTag.setCreatNum(1);
			    	 }
			    	 userTag.setInsertTime(new Date());
			    	 userTag.setUpdateTime(new Date());
			    	 addUserTag(userTag);
			     }else{
			    	 if("read".equals(way)){
			    		 count = userTag.getReadNum()==null?0:userTag.getReadNum();
				    	 userTag.setReadNum(++count);
			    	 }else if("share".equals(way)){
			    		 count = userTag.getShareNum()==null?0:userTag.getShareNum();
				    	 userTag.setShareNum(++count);
			    	 }else{//add
			    		 count = userTag.getCreatNum()==null?0:userTag.getCreatNum();
				    	 userTag.setCreatNum(++count);
			    	 }
			    	 userTag.setUpdateTime(new Date());
			    	 updateUserTag(userTag);
			     }
			 }
		 }
	}

	

	
	
}
