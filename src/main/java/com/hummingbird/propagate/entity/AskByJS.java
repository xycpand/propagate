package com.hummingbird.propagate.entity;

public class AskByJS {
	  
	private String openId;
    /**
     * js方式请求的内容id
     */
    private Long contentId;
	
    
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
