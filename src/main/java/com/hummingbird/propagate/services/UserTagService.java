package com.hummingbird.propagate.services;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.propagate.vo.AddArticleTagBodyVO;
import com.hummingbird.propagate.vo.OpenidBodyVO;
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

	public void addArticleTag(AddArticleTagBodyVO body)throws BusinessException;
	
}
