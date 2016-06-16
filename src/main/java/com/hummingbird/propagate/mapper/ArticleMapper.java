package com.hummingbird.propagate.mapper;

import com.hummingbird.propagate.entity.Article;

public interface ArticleMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Article record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Article record);

    /**
     * 根据主键查询记录
     */
    Article selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKeyWithBLOBs(Article record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Article record);
}