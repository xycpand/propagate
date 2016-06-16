package com.hummingbird.propagate.entity;

import java.util.Date;

public class WxUser {
    private Integer userid;

    private String openid;

    private String nickname;

    private Byte sex;

    private String language;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private Integer subscribe_time;

    private String unionid;

    private String remark;

    private Byte subscribe;

    private Integer groupid;

    private Date insert_time;

    private Date update_time;

    private String qr_ticket;

    private Integer qr_expire_seconds;

    private Integer qr_create_time;

    private String tagid_list;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public Integer getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(Integer subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Byte subscribe) {
        this.subscribe = subscribe;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getQr_ticket() {
        return qr_ticket;
    }

    public void setQr_ticket(String qr_ticket) {
        this.qr_ticket = qr_ticket == null ? null : qr_ticket.trim();
    }

    public Integer getQr_expire_seconds() {
        return qr_expire_seconds;
    }

    public void setQr_expire_seconds(Integer qr_expire_seconds) {
        this.qr_expire_seconds = qr_expire_seconds;
    }

    public Integer getQr_create_time() {
        return qr_create_time;
    }

    public void setQr_create_time(Integer qr_create_time) {
        this.qr_create_time = qr_create_time;
    }

    public String getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(String tagid_list) {
        this.tagid_list = tagid_list == null ? null : tagid_list.trim();
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
        WxUser other = (WxUser) that;
        return (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getLanguage() == null ? other.getLanguage() == null : this.getLanguage().equals(other.getLanguage()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getHeadimgurl() == null ? other.getHeadimgurl() == null : this.getHeadimgurl().equals(other.getHeadimgurl()))
            && (this.getSubscribe_time() == null ? other.getSubscribe_time() == null : this.getSubscribe_time().equals(other.getSubscribe_time()))
            && (this.getUnionid() == null ? other.getUnionid() == null : this.getUnionid().equals(other.getUnionid()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getSubscribe() == null ? other.getSubscribe() == null : this.getSubscribe().equals(other.getSubscribe()))
            && (this.getGroupid() == null ? other.getGroupid() == null : this.getGroupid().equals(other.getGroupid()))
            && (this.getInsert_time() == null ? other.getInsert_time() == null : this.getInsert_time().equals(other.getInsert_time()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
            && (this.getQr_ticket() == null ? other.getQr_ticket() == null : this.getQr_ticket().equals(other.getQr_ticket()))
            && (this.getQr_expire_seconds() == null ? other.getQr_expire_seconds() == null : this.getQr_expire_seconds().equals(other.getQr_expire_seconds()))
            && (this.getQr_create_time() == null ? other.getQr_create_time() == null : this.getQr_create_time().equals(other.getQr_create_time()))
            && (this.getTagid_list() == null ? other.getTagid_list() == null : this.getTagid_list().equals(other.getTagid_list()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getLanguage() == null) ? 0 : getLanguage().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getHeadimgurl() == null) ? 0 : getHeadimgurl().hashCode());
        result = prime * result + ((getSubscribe_time() == null) ? 0 : getSubscribe_time().hashCode());
        result = prime * result + ((getUnionid() == null) ? 0 : getUnionid().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getSubscribe() == null) ? 0 : getSubscribe().hashCode());
        result = prime * result + ((getGroupid() == null) ? 0 : getGroupid().hashCode());
        result = prime * result + ((getInsert_time() == null) ? 0 : getInsert_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getQr_ticket() == null) ? 0 : getQr_ticket().hashCode());
        result = prime * result + ((getQr_expire_seconds() == null) ? 0 : getQr_expire_seconds().hashCode());
        result = prime * result + ((getQr_create_time() == null) ? 0 : getQr_create_time().hashCode());
        result = prime * result + ((getTagid_list() == null) ? 0 : getTagid_list().hashCode());
        return result;
    }
}