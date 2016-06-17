package com.hummingbird.propagate.vo;

public class SaveUserRecordVO {
	/**
	* 用户令牌
	*/
    private String token;
	  
	  
	private String x_reader;
	
	private String x_sharer;
	
    /**
    * 内容id
    */
    private String x_content;

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getX_reader() {
		return x_reader;
	}
	
	public void setX_reader(String x_reader) {
		this.x_reader = x_reader;
	}
	
	public String getX_sharer() {
		return x_sharer;
	}
	
	public void setX_sharer(String x_sharer) {
		this.x_sharer = x_sharer;
	}
	
	public String getX_content() {
		return x_content;
	}
	
	public void setX_content(String x_content) {
		this.x_content = x_content;
	}
    
}
