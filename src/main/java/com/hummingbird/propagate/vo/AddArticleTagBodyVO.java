package com.hummingbird.propagate.vo;

import java.util.List;

public class AddArticleTagBodyVO {
	private String articleId;
	private String openid;
	private List<String> tagName;
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public List<String> getTagName() {
		return tagName;
	}
	public void setTagName(List<String> tagName) {
		this.tagName = tagName;
	}
	

}
