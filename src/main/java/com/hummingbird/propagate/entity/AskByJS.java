package com.hummingbird.propagate.entity;

public class AskByJS {
	  
	private String openId;
    /**
     * js方式请求的内容id
     */
    private Long contentId;
    
    private String path;
    
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
}
