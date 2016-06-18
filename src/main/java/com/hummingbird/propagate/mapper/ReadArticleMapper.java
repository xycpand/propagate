package com.hummingbird.propagate.mapper;

import com.hummingbird.propagate.entity.ReadArticle;

public interface ReadArticleMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ReadArticle record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ReadArticle record);

    /**
     * 根据主键查询记录
     */
    ReadArticle selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ReadArticle record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ReadArticle record);
}