package com.hummingbird.propagate.mapper;

import com.hummingbird.propagate.entity.ShareArticle;

public interface ShareArticleMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ShareArticle record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ShareArticle record);

    /**
     * 根据主键查询记录
     */
    ShareArticle selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ShareArticle record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ShareArticle record);
}