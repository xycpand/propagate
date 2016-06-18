package com.hummingbird.propagate.entity;

import java.util.Date;

/**
 * 阅读记录表
 */
public class ReadArticle {
    private Integer id;

    /**
     * 分享内容表id
     */
    private Integer articleId;

    /**
     * 微信用户表id
     */
    private String userid;

    /**
     * 阅读时的链接
     */
    private String originalUrl;

    /**
     * 阅读时原分享者
     */
    private String originalUserid;

    /**
     * 插入时间
     */
    private Date insertTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 分享内容表id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * @param articleId 
	 *            分享内容表id
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * @return 微信用户表id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid 
	 *            微信用户表id
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
     * @return 阅读时原分享者
     */
    public String getOriginalUserid() {
        return originalUserid;
    }

    /**
     * @param originalUserid 
	 *            阅读时原分享者
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
        ReadArticle other = (ReadArticle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getOriginalUrl() == null ? other.getOriginalUrl() == null : this.getOriginalUrl().equals(other.getOriginalUrl()))
            && (this.getOriginalUserid() == null ? other.getOriginalUserid() == null : this.getOriginalUserid().equals(other.getOriginalUserid()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()));
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
        return result;
    }
}