package com.hummingbird.propagate.vo;

/**
 * 阅读记录表
 */
public class SaveReadArticleVO {

    /**
     * 分享内容表id
     */
    private String articleId;

    /**
     * 阅读者openId
     */
    private String openId;

    /**
     * 阅读时的链接
     */
    private String originalUrl;

    /**
     * 阅读时原分享者opendId
     */
    private String originalOpenId;
    /**
     * 分享类型
     */
    private String shareType;

    /**
     * 分享目标
     */
    private String shareTarget;

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

	public String getOriginalOpenId() {
		return originalOpenId;
	}

	public void setOriginalOpenId(String originalOpenId) {
		this.originalOpenId = originalOpenId;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public String getShareTarget() {
		return shareTarget;
	}

	public void setShareTarget(String shareTarget) {
		this.shareTarget = shareTarget;
	}
}