package com.hummingbird.propagate.entity;

import java.util.Date;

/**
 * 分享内容表
 */
public class Article {
    /**
     * 文章主键，由其它公司传入
     */
    private String id;

    private Integer userid;

    /**
     * 分享标题
     */
    private String title;

    private Date insertTime;

    /**
     * OK#正常，OFF禁用，
     */
    private String status;

    private Date updateTime;

    /**
     * 分享内容
     */
    private String content;

    /**
     * @return 文章主键，由其它公司传入
     */
    public String getId() {
        return id;
    }

    /**
     * @param id 
	 *            文章主键，由其它公司传入
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return 分享标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title 
	 *            分享标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * @return OK#正常，OFF禁用，
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            OK#正常，OFF禁用，
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 分享内容
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content 
	 *            分享内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        Article other = (Article) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
}