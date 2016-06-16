package com.hummingbird.propagate.entity;

import java.util.Date;

/**
 * 用户浏览记录
 */
public class UserRecord {
    private Long id;

    private String openId;

    /**
     * 内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    private Long contentId;

    private Date insertTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * @return 内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * @param contentid 
	 *            内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
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
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getContentId() == null ? other.getContentId() == null : this.getContentId().equals(other.getContentId()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getContentId() == null) ? 0 : getContentId().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        return result;
    }
}