package com.hummingbird.propagate.entity;

/**
 * 微信公众号设置表
 */
public class WechatSetting {
    private Integer id;

    /**
     * appid
     */
    private String appid;

    /**
     * app密钥
     */
    private String appsecret;

    /**
     * token
     */
    private String token;

    /**
     * 加密串
     */
    private String encodingaeskey;

    private Byte debug;

    private String logcallback;

    /**
     * 公众号名称
     */
    private String name;

    /**
     * 收款商户id
     */
    private String merId;

    /**
     * 收款商户密钥
     */
    private String merKey;

    /**
     * 获取到的凭证
     */
    private String accessToken;

    /**
     * token有效时间，单位：秒 
     */
    private Integer expiresIn;

    /**
     * access_token获取时间
     */
    private Integer tokenCtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * @param appid 
	 *            appid
     */
    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    /**
     * @return app密钥
     */
    public String getAppsecret() {
        return appsecret;
    }

    /**
     * @param appsecret 
	 *            app密钥
     */
    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token 
	 *            token
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * @return 加密串
     */
    public String getEncodingaeskey() {
        return encodingaeskey;
    }

    /**
     * @param encodingaeskey 
	 *            加密串
     */
    public void setEncodingaeskey(String encodingaeskey) {
        this.encodingaeskey = encodingaeskey == null ? null : encodingaeskey.trim();
    }

    public Byte getDebug() {
        return debug;
    }

    public void setDebug(Byte debug) {
        this.debug = debug;
    }

    public String getLogcallback() {
        return logcallback;
    }

    public void setLogcallback(String logcallback) {
        this.logcallback = logcallback == null ? null : logcallback.trim();
    }

    /**
     * @return 公众号名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            公众号名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 收款商户id
     */
    public String getMerId() {
        return merId;
    }

    /**
     * @param merId 
	 *            收款商户id
     */
    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    /**
     * @return 收款商户密钥
     */
    public String getMerKey() {
        return merKey;
    }

    /**
     * @param merKey 
	 *            收款商户密钥
     */
    public void setMerKey(String merKey) {
        this.merKey = merKey == null ? null : merKey.trim();
    }

    /**
     * @return 获取到的凭证
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken 
	 *            获取到的凭证
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * @return token有效时间，单位：秒 
     */
    public Integer getExpiresIn() {
        return expiresIn;
    }

    /**
     * @param expiresIn 
	 *            token有效时间，单位：秒 
     */
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * @return access_token获取时间
     */
    public Integer getTokenCtime() {
        return tokenCtime;
    }

    /**
     * @param tokenCtime 
	 *            access_token获取时间
     */
    public void setTokenCtime(Integer tokenCtime) {
        this.tokenCtime = tokenCtime;
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
        WechatSetting other = (WechatSetting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppid() == null ? other.getAppid() == null : this.getAppid().equals(other.getAppid()))
            && (this.getAppsecret() == null ? other.getAppsecret() == null : this.getAppsecret().equals(other.getAppsecret()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getEncodingaeskey() == null ? other.getEncodingaeskey() == null : this.getEncodingaeskey().equals(other.getEncodingaeskey()))
            && (this.getDebug() == null ? other.getDebug() == null : this.getDebug().equals(other.getDebug()))
            && (this.getLogcallback() == null ? other.getLogcallback() == null : this.getLogcallback().equals(other.getLogcallback()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMerId() == null ? other.getMerId() == null : this.getMerId().equals(other.getMerId()))
            && (this.getMerKey() == null ? other.getMerKey() == null : this.getMerKey().equals(other.getMerKey()))
            && (this.getAccessToken() == null ? other.getAccessToken() == null : this.getAccessToken().equals(other.getAccessToken()))
            && (this.getExpiresIn() == null ? other.getExpiresIn() == null : this.getExpiresIn().equals(other.getExpiresIn()))
            && (this.getTokenCtime() == null ? other.getTokenCtime() == null : this.getTokenCtime().equals(other.getTokenCtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAppid() == null) ? 0 : getAppid().hashCode());
        result = prime * result + ((getAppsecret() == null) ? 0 : getAppsecret().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getEncodingaeskey() == null) ? 0 : getEncodingaeskey().hashCode());
        result = prime * result + ((getDebug() == null) ? 0 : getDebug().hashCode());
        result = prime * result + ((getLogcallback() == null) ? 0 : getLogcallback().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMerId() == null) ? 0 : getMerId().hashCode());
        result = prime * result + ((getMerKey() == null) ? 0 : getMerKey().hashCode());
        result = prime * result + ((getAccessToken() == null) ? 0 : getAccessToken().hashCode());
        result = prime * result + ((getExpiresIn() == null) ? 0 : getExpiresIn().hashCode());
        result = prime * result + ((getTokenCtime() == null) ? 0 : getTokenCtime().hashCode());
        return result;
    }
}