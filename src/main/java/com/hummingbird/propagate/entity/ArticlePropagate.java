package com.hummingbird.propagate.entity;

import java.util.Date;

public class ArticlePropagate {
    private Integer id;

    /**
     * 分享内容表的id
     */
    private String articleId;

    /**
     * 分享内容的名称
     */
    private String articleName;

    /**
     * 微信用户表id
     */
    private Integer userid;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 上级节点userid
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private Date insertTime;

    /**
     * 状态，OK#为正常，OFF表示该节点及以下节点都不可见，
     */
    private String status;

    /**
     * 保留字段
     */
    private String remark;

    /**
     * 以&分隔
     */
    private String treeNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 分享内容表的id
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * @param articleId 
	 *            分享内容表的id
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    /**
     * @return 分享内容的名称
     */
    public String getArticleName() {
        return articleName;
    }

    /**
     * @param articleName 
	 *            分享内容的名称
     */
    public void setArticleName(String articleName) {
        this.articleName = articleName == null ? null : articleName.trim();
    }

    /**
     * @return 微信用户表id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid 
	 *            微信用户表id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return 用户昵称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            用户昵称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 上级节点userid
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId 
	 *            上级节点userid
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return 创建时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime 
	 *            创建时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * @return 状态，OK#为正常，OFF表示该节点及以下节点都不可见，
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态，OK#为正常，OFF表示该节点及以下节点都不可见，
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return 保留字段
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            保留字段
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return 以&分隔
     */
    public String getTreeNo() {
        return treeNo;
    }

    /**
     * @param treeNo 
	 *            以&分隔
     */
    public void setTreeNo(String treeNo) {
        this.treeNo = treeNo == null ? null : treeNo.trim();
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
        ArticlePropagate other = (ArticlePropagate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getArticleName() == null ? other.getArticleName() == null : this.getArticleName().equals(other.getArticleName()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getTreeNo() == null ? other.getTreeNo() == null : this.getTreeNo().equals(other.getTreeNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getArticleName() == null) ? 0 : getArticleName().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getTreeNo() == null) ? 0 : getTreeNo().hashCode());
        return result;
    }
}