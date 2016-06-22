package com.hummingbird.propagate.entity;

import java.util.Date;

/**
 * 用户画像表
 */
public class UserTag {
    private Integer id;

    /**
     * 微信用户id
     */
    private Integer userid;

    /**
     * 标签id
     */
    private Integer tagId;

    /**
     * 分享该标签次数
     */
    private Integer shareNum;

    /**
     * 阅读该标签次数
     */
    private Integer readNum;

    /**
     * 创建该标签次数
     */
    private Integer creatNum;

    /**
     * 插入时间
     */
    private Date insertTime;

    /**
     * 更新时间
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
     * @return 标签id
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * @param tagId 
	 *            标签id
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    /**
     * @return 分享该标签次数
     */
    public Integer getShareNum() {
        return shareNum;
    }

    /**
     * @param shareNum 
	 *            分享该标签次数
     */
    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    /**
     * @return 阅读该标签次数
     */
    public Integer getReadNum() {
        return readNum;
    }

    /**
     * @param readNum 
	 *            阅读该标签次数
     */
    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    /**
     * @return 创建该标签次数
     */
    public Integer getCreatNum() {
        return creatNum;
    }

    /**
     * @param creatNum 
	 *            创建该标签次数
     */
    public void setCreatNum(Integer creatNum) {
        this.creatNum = creatNum;
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
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            更新时间
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
        UserTag other = (UserTag) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()))
            && (this.getShareNum() == null ? other.getShareNum() == null : this.getShareNum().equals(other.getShareNum()))
            && (this.getReadNum() == null ? other.getReadNum() == null : this.getReadNum().equals(other.getReadNum()))
            && (this.getCreatNum() == null ? other.getCreatNum() == null : this.getCreatNum().equals(other.getCreatNum()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        result = prime * result + ((getShareNum() == null) ? 0 : getShareNum().hashCode());
        result = prime * result + ((getReadNum() == null) ? 0 : getReadNum().hashCode());
        result = prime * result + ((getCreatNum() == null) ? 0 : getCreatNum().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}