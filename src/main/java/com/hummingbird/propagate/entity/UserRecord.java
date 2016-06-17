package com.hummingbird.propagate.entity;

import java.util.Date;

/**
 * 用户浏览记录
 */
public class UserRecord {
    private Long id;

    /**
     * 阅读者openId
     */
    private String readerId;

    /**
     * 分享者openId
     */
    private String sharerId;

    /**
     * 内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    private String contentId;

    private Date insertTime;

    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 阅读者openId
     */
    public String getReaderId() {
        return readerId;
    }

    /**
     * @param readerid 
	 *            阅读者openId
     */
    public void setReaderId(String readerId) {
        this.readerId = readerId == null ? null : readerId.trim();
    }

    /**
     * @return 分享者openId
     */
    public String getSharerId() {
        return sharerId;
    }

    /**
     * @param sharerid 
	 *            分享者openId
     */
    public void setSharerId(String sharerId) {
        this.sharerId = sharerId == null ? null : sharerId.trim();
    }

    /**
     * @return 内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * @param contentid 
	 *            内容id,为每个页面的唯一标识,在x传播里面必须唯一,可以由x传播生成,或由使用方给出
     */
    public void setContentId(String contentId) {
        this.contentId = contentId == null ? null : contentId.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
            && (this.getReaderId() == null ? other.getReaderId() == null : this.getReaderId().equals(other.getReaderId()))
            && (this.getSharerId() == null ? other.getSharerId() == null : this.getSharerId().equals(other.getSharerId()))
            && (this.getContentId() == null ? other.getContentId() == null : this.getContentId().equals(other.getContentId()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getReaderId() == null) ? 0 : getReaderId().hashCode());
        result = prime * result + ((getSharerId() == null) ? 0 : getSharerId().hashCode());
        result = prime * result + ((getContentId() == null) ? 0 : getContentId().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}