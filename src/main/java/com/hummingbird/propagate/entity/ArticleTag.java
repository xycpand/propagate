package com.hummingbird.propagate.entity;

/**
 * 分享内容及标签关系表
 */
public class ArticleTag {
    private Integer id;

    /**
     * 分享内容表id
     */
    private String article_id;

    /**
     * 分享内容使用的标签id
     */
    private Integer tag_id;

    /**
     * 使用标签名称
     */
    private String tag_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 分享内容表id
     */
    public String getArticle_id() {
        return article_id;
    }

    /**
     * @param articleId 
	 *            分享内容表id
     */
    public void setArticle_id(String article_id) {
        this.article_id = article_id == null ? null : article_id.trim();
    }

    /**
     * @return 分享内容使用的标签id
     */
    public Integer getTag_id() {
        return tag_id;
    }

    /**
     * @param tagId 
	 *            分享内容使用的标签id
     */
    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    /**
     * @return 使用标签名称
     */
    public String getTag_name() {
        return tag_name;
    }

    /**
     * @param tagName 
	 *            使用标签名称
     */
    public void setTag_name(String tag_name) {
        this.tag_name = tag_name == null ? null : tag_name.trim();
    }
}