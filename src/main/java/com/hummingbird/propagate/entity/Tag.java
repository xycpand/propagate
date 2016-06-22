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
    private Integer type_id;

    /**
     * 标签名
     */
    private String tag_name;

    /**
     * 热度，使用次数
     */
    private Integer use_num;

    /**
     * 排序
     */
    private Integer sotno;

    /**
     * 插入时间
     */
    private Date insert_time;

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
    private Date update_time;

    /**
     * 更新备注
     */
    private String update_remark;

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
    public Integer getType_id() {
        return type_id;
    }

    /**
     * @param typeId 
	 *            标签类型表id
     */
    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    /**
     * @return 标签名
     */
    public String getTag_name() {
        return tag_name;
    }

    /**
     * @param tagName 
	 *            标签名
     */
    public void setTag_name(String tag_name) {
        this.tag_name = tag_name == null ? null : tag_name.trim();
    }

    /**
     * @return 热度，使用次数
     */
    public Integer getUse_num() {
        return use_num;
    }

    /**
     * @param useNum 
	 *            热度，使用次数
     */
    public void setUse_num(Integer use_num) {
        this.use_num = use_num;
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
    public Date getInsert_time() {
        return insert_time;
    }

    /**
     * @param insertTime 
	 *            插入时间
     */
    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
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
    public Date getUpdate_time() {
        return update_time;
    }

    /**
     * @param updateTime 
	 *            更新时间
     */
    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    /**
     * @return 更新备注
     */
    public String getUpdate_remark() {
        return update_remark;
    }

    /**
     * @param updateRemark 
	 *            更新备注
     */
    public void setUpdate_remark(String update_remark) {
        this.update_remark = update_remark == null ? null : update_remark.trim();
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