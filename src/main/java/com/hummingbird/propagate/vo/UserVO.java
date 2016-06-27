package com.hummingbird.propagate.vo;

import java.util.Date;

public class UserVO {
    private Integer userid;

    private String openid;

    private String nickname;

    private String sex;

    private String language;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private String subscribeTime;

    private String unionid;

    private String remark;

    private String subscribe;

    private String groupid;

    private Date insertTime;

    private Date updateTime;

    private String qrTicket;

    private String qrExpireSeconds;

    private String qrCreateTime;

    private String tagidList;

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


    public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
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

    public String getQrTicket() {
        return qrTicket;
    }

    public void setQrTicket(String qrTicket) {
        this.qrTicket = qrTicket == null ? null : qrTicket.trim();
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList == null ? null : tagidList.trim();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((groupid == null) ? 0 : groupid.hashCode());
		result = prime * result + ((headimgurl == null) ? 0 : headimgurl.hashCode());
		result = prime * result + ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((openid == null) ? 0 : openid.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
		result = prime * result + ((qrCreateTime == null) ? 0 : qrCreateTime.hashCode());
		result = prime * result + ((qrExpireSeconds == null) ? 0 : qrExpireSeconds.hashCode());
		result = prime * result + ((qrTicket == null) ? 0 : qrTicket.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((subscribe == null) ? 0 : subscribe.hashCode());
		result = prime * result + ((subscribeTime == null) ? 0 : subscribeTime.hashCode());
		result = prime * result + ((tagidList == null) ? 0 : tagidList.hashCode());
		result = prime * result + ((unionid == null) ? 0 : unionid.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (groupid == null) {
			if (other.groupid != null)
				return false;
		} else if (!groupid.equals(other.groupid))
			return false;
		if (headimgurl == null) {
			if (other.headimgurl != null)
				return false;
		} else if (!headimgurl.equals(other.headimgurl))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (openid == null) {
			if (other.openid != null)
				return false;
		} else if (!openid.equals(other.openid))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (qrCreateTime == null) {
			if (other.qrCreateTime != null)
				return false;
		} else if (!qrCreateTime.equals(other.qrCreateTime))
			return false;
		if (qrExpireSeconds == null) {
			if (other.qrExpireSeconds != null)
				return false;
		} else if (!qrExpireSeconds.equals(other.qrExpireSeconds))
			return false;
		if (qrTicket == null) {
			if (other.qrTicket != null)
				return false;
		} else if (!qrTicket.equals(other.qrTicket))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (subscribe == null) {
			if (other.subscribe != null)
				return false;
		} else if (!subscribe.equals(other.subscribe))
			return false;
		if (subscribeTime == null) {
			if (other.subscribeTime != null)
				return false;
		} else if (!subscribeTime.equals(other.subscribeTime))
			return false;
		if (tagidList == null) {
			if (other.tagidList != null)
				return false;
		} else if (!tagidList.equals(other.tagidList))
			return false;
		if (unionid == null) {
			if (other.unionid != null)
				return false;
		} else if (!unionid.equals(other.unionid))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

}