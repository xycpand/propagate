package com.hummingbird.propagate.entity;

import java.util.Date;

/**
 * 标签表
 */
public class Tag {
    private Integer id;

    /**
     * 标签类型表id
     */
    private Integer typeId;

    /**
     * 标签名，名称唯一，被使用后不可修改，删除名称
     */
    private String tagName;

    /**
     * 热度，使用次数
     */
    private Integer useNum;

    /**
     * 排序
     */
    private Integer sotno;

    /**
     * 插入时间
     */
    private Date insertTime;

    /**
     * 状态，OK#正常，OFF下线
     */
    private String status;

    /**
     * 创建者id
     */
    private Integer userid;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新备注
     */
    private String updateRemark;

    /**
     * 是否推荐，是YES，否NO#
     */
    private String isrecommend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 标签类型表id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId 
	 *            标签类型表id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * @return 标签名，名称唯一，被使用后不可修改，删除名称
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * @param tagName 
	 *            标签名，名称唯一，被使用后不可修改，删除名称
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    /**
     * @return 热度，使用次数
     */
    public Integer getUseNum() {
        return useNum;
    }

    /**
     * @param useNum 
	 *            热度，使用次数
     */
    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    /**
     * @return 排序
     */
    public Integer getSotno() {
        return sotno;
    }

    /**
     * @param sotno 
	 *            排序
     */
    public void setSotno(Integer sotno) {
        this.sotno = sotno;
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
     * @return 状态，OK#正常，OFF下线
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            状态，OK#正常，OFF下线
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * @return 创建者id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid 
	 *            创建者id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
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

    /**
     * @return 更新备注
     */
    public String getUpdateRemark() {
        return updateRemark;
    }

    /**
     * @param updateRemark 
	 *            更新备注
     */
    public void setUpdateRemark(String updateRemark) {
        this.updateRemark = updateRemark == null ? null : updateRemark.trim();
    }

    /**
     * @return 是否推荐，是YES，否NO#
     */
    public String getIsrecommend() {
        return isrecommend;
    }

    /**
     * @param isrecommend 
	 *            是否推荐，是YES，否NO#
     */
    public void setIsrecommend(String isrecommend) {
        this.isrecommend = isrecommend == null ? null : isrecommend.trim();
    }
}