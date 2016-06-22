package com.hummingbird.propagate.entity;

/**
 * 分享内容及标签关系表
 */
public class ArticleTag {
    private Integer id;

    /**
     * 分享内容表id
     */
    private String articleId;

    /**
     * 分享内容使用的标签id
     */
    private Integer tagId;

    /**
     * 使用标签名称
     */
    private String tagName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 分享内容表id
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * @param articleId 
	 *            分享内容表id
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    /**
     * @return 分享内容使用的标签id
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * @param tagId 
	 *            分享内容使用的标签id
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    /**
     * @return 使用标签名称
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * @param tagName 
	 *            使用标签名称
     */
    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
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
        ArticleTag other = (ArticleTag) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()))
            && (this.getTagName() == null ? other.getTagName() == null : this.getTagName().equals(other.getTagName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        result = prime * result + ((getTagName() == null) ? 0 : getTagName().hashCode());
        return result;
    }
}