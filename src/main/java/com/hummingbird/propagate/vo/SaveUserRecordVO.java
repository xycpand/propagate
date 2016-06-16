package com.hummingbird.propagate.vo;

public class SaveUserRecordVO {
	/**
	* 用户令牌
	*/
    private String token;
	  
	private String openid;

    /**
     * 内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    private Long articleId;
    /**
     * js方式请求的内容id
     */
    private Long contentId;
    
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

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

}
