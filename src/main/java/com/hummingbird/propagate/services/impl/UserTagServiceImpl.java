package com.hummingbird.propagate.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.propagate.entity.Article;
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
		ValidateUtil.assertNullnoappend(article, "文章信息记录未找到！");
		if(article.getUserid()!=wxUser.getUserid()){
			
		}
		//
		
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
