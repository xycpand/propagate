package com.hummingbird.propagate.entity;

import java.util.Date;

/**
 * 分享记录表
 */
public class ShareArticle {
    private Integer id;
    /**
     * 分享内容表id
     */
    private Integer articleId;

    /**
     * 微信用户id
     */
    private String userid;

    /**
     * 阅读时的链接
     */
    private String originalUrl;

    /**
     * 分享者用户id
     */
    private String originalUserid;

    /**
     * 插入时间
     */
    private Date insertTime;

    /**
     * 分享类型
     */
    private String shareType;

    /**
     * 分享时间
     */
    private Date shareTime;

    /**
     * 分享目标
     */
    private String shareTarget;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	/**
     * @return 微信用户id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid 
	 *            微信用户id
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * @return 阅读时的链接
     */
    public String getOriginalUrl() {
        return originalUrl;
    }

    /**
     * @param originalUrl 
	 *            阅读时的链接
     */
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl == null ? null : originalUrl.trim();
    }

    /**
     * @return 分享者用户id
     */
    public String getOriginalUserid() {
        return originalUserid;
    }

    /**
     * @param originalUserid 
	 *            分享者用户id
     */
    public void setOriginalUserid(String originalUserid) {
        this.originalUserid = originalUserid == null ? null : originalUserid.trim();
    }

    /**
     * @return 插入时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime 
	 *            插入时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * @return 分享类型
     */
    public String getShareType() {
        return shareType;
    }

    /**
     * @param shareType 
	 *            分享类型
     */
    public void setShareType(String shareType) {
        this.shareType = shareType == null ? null : shareType.trim();
    }

    /**
     * @return 分享时间
     */
    public Date getShareTime() {
        return shareTime;
    }

    /**
     * @param shareTime 
	 *            分享时间
     */
    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    /**
     * @return 分享目标
     */
    public String getShareTarget() {
        return shareTarget;
    }

    /**
     * @param shareTarget 
	 *            分享目标
     */
    public void setShareTarget(String shareTarget) {
        this.shareTarget = shareTarget == null ? null : shareTarget.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ShareArticle other = (ShareArticle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getOriginalUrl() == null ? other.getOriginalUrl() == null : this.getOriginalUrl().equals(other.getOriginalUrl()))
            && (this.getOriginalUserid() == null ? other.getOriginalUserid() == null : this.getOriginalUserid().equals(other.getOriginalUserid()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getShareType() == null ? other.getShareType() == null : this.getShareType().equals(other.getShareType()))
            && (this.getShareTime() == null ? other.getShareTime() == null : this.getShareTime().equals(other.getShareTime()))
            && (this.getShareTarget() == null ? other.getShareTarget() == null : this.getShareTarget().equals(other.getShareTarget()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getOriginalUrl() == null) ? 0 : getOriginalUrl().hashCode());
        result = prime * result + ((getOriginalUserid() == null) ? 0 : getOriginalUserid().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getShareType() == null) ? 0 : getShareType().hashCode());
        result = prime * result + ((getShareTime() == null) ? 0 : getShareTime().hashCode());
        result = prime * result + ((getShareTarget() == null) ? 0 : getShareTarget().hashCode());
        return result;
    }
}