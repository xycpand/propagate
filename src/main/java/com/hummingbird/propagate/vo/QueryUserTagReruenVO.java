package com.hummingbird.propagate.vo;

import java.util.List;

//查询用户标签
public class QueryUserTagReruenVO {
	private List<TagVO> userTags;

	public List<TagVO> getUserTags() {
		return userTags;
	}

	public void setUserTags(List<TagVO> userTags) {
		this.userTags = userTags;
	}
	
}
