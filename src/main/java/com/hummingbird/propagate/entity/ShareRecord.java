package com.hummingbird.propagate.entity;

import java.util.Date;

/**
 * 用户分享记录表
 */
public class ShareRecord {
    private Integer id;

    /**
     * 微信用户id
     */
    private Integer userid;

    /**
     * 分享内容表t_article的id
     */
    private String articleId;

    /**
     * 分享时填写的内容
     */
    private String remark;

    /**
     * 阅读时的链接
     */
    private String originalUrl;

    /**
     * 分享类型
     */
    private String shareType;

    /**
     * 分享目标
     */
    private String shareTarget;

    /**
     * 插入时间
     */
    private Date insertTime;

    /**
     * 插入时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 微信用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid 
	 *            微信用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return 分享内容表t_article的id
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * @param articleId 
	 *            分享内容表t_article的id
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    /**
     * @return 分享时填写的内容
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            分享时填写的内容
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
     * @return 插入时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            插入时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        ShareRecord other = (ShareRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getOriginalUrl() == null ? other.getOriginalUrl() == null : this.getOriginalUrl().equals(other.getOriginalUrl()))
            && (this.getShareType() == null ? other.getShareType() == null : this.getShareType().equals(other.getShareType()))
            && (this.getShareTarget() == null ? other.getShareTarget() == null : this.getShareTarget().equals(other.getShareTarget()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getOriginalUrl() == null) ? 0 : getOriginalUrl().hashCode());
        result = prime * result + ((getShareType() == null) ? 0 : getShareType().hashCode());
        result = prime * result + ((getShareTarget() == null) ? 0 : getShareTarget().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}