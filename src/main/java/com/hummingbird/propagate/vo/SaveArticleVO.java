package com.hummingbird.propagate.vo;

public class SaveArticleVO {
	               
	 /**
     * 文章主键，由其它公司传入
     */
    private String articleId;

    private Integer userid;

    /**
     * 分享标题
     */
    private String title;

    /**
     * 分享内容
     */
    private String content;

    /**
     * 分享内容使用的标签id，以&分隔
     */
    private String tagIds;

    /**
     * 使用标签名称，以&分隔
     */
    private String tagNames;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}
}
