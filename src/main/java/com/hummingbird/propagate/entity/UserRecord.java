package com.hummingbird.propagate.entity;

import java.util.Date;

/**
 * 用户浏览记录
 */
public class UserRecord {
    private Integer id;

    private String openid;

    /**
     * 内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    private Integer articleId;

    private Date insertTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    /**
     * @return 内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * @param articleid 
	 *            内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

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
        UserRecord other = (UserRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        return result;
    }
}