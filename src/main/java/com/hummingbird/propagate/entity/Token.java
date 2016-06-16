package com.hummingbird.propagate.entity;

import java.util.Date;

/**
 * t_user_token
 */
public class Token {
  

	/**
     * 用户令牌
     */
    private String token;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 有效时间长度（单位：秒）
     */
    private Integer expireIn;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 创建时间（插入时间）
     */
    private Date insertTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * @return 用户令牌
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token 
	 *            用户令牌
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

   

    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(Integer expireIn) {
		this.expireIn = expireIn;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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
        Token other = (Token) that;
        return (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getExpireIn() == null ? other.getExpireIn() == null : this.getExpireIn().equals(other.getExpireIn()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getInsertTime() == null ? other.getInsertTime() == null : this.getInsertTime().equals(other.getInsertTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getExpireIn() == null) ? 0 : getExpireIn().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getInsertTime() == null) ? 0 : getInsertTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

}