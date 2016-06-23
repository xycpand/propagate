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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articleId == null) ? 0 : articleId.hashCode());
		result = prime * result + ((openId == null) ? 0 : openId.hashCode());
		result = prime * result + ((originalOpenId == null) ? 0 : originalOpenId.hashCode());
		result = prime * result + ((originalUrl == null) ? 0 : originalUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaveReadArticleVO other = (SaveReadArticleVO) obj;
		if (articleId == null) {
			if (other.articleId != null)
				return false;
		} else if (!articleId.equals(other.articleId))
			return false;
		if (openId == null) {
			if (other.openId != null)
				return false;
		} else if (!openId.equals(other.openId))
			return false;
		if (originalOpenId == null) {
			if (other.originalOpenId != null)
				return false;
		} else if (!originalOpenId.equals(other.originalOpenId))
			return false;
		if (originalUrl == null) {
			if (other.originalUrl != null)
				return false;
		} else if (!originalUrl.equals(other.originalUrl))
			return false;
		return true;
	}
}