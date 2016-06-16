package com.hummingbird.propagate.vo;

import java.util.Date;

public class SaveUserRecordVO {
	/**
	* 用户令牌
	*/
    private String token;
	  
	private String openid;

    /**
     * 内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    private Integer articleId;

    private Date insertTime;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
}
