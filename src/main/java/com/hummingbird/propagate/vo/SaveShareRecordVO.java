package com.hummingbird.propagate.vo;

public class SaveShareRecordVO {
	               
	 /**
     * 文章主键
     */
    private String articleId;

    /**
     * 分享者openId
     */
    private String openId;
    /**
     * 阅读时的链接
     */
    private String originalUrl;

    /**
     * 分享类型
     */
    private String shareType;


	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}


	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

}
