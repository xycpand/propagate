package com.hummingbird.propagate.entity;

/**
 * 分享内容及标签关系表
 */
public class ArticleTag {
    private Integer id;

    /**
     * 分享内容表id
     */
    private Integer articleId;

    /**
     * 分享内容使用的标签id，以&分隔
     */
    private String tagIds;

    /**
     * 使用标签名称，以&分隔
     */
    private String tagNames;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 分享内容表id
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * @param articleId 
	 *            分享内容表id
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * @return 分享内容使用的标签id，以&分隔
     */
    public String getTagIds() {
        return tagIds;
    }

    /**
     * @param tagIds 
	 *            分享内容使用的标签id，以&分隔
     */
    public void setTagIds(String tagIds) {
        this.tagIds = tagIds == null ? null : tagIds.trim();
    }

    /**
     * @return 使用标签名称，以&分隔
     */
    public String getTagNames() {
        return tagNames;
    }

    /**
     * @param tagNames 
	 *            使用标签名称，以&分隔
     */
    public void setTagNames(String tagNames) {
        this.tagNames = tagNames == null ? null : tagNames.trim();
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
            && (this.getTagIds() == null ? other.getTagIds() == null : this.getTagIds().equals(other.getTagIds()))
            && (this.getTagNames() == null ? other.getTagNames() == null : this.getTagNames().equals(other.getTagNames()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getTagIds() == null) ? 0 : getTagIds().hashCode());
        result = prime * result + ((getTagNames() == null) ? 0 : getTagNames().hashCode());
        return result;
    }
}