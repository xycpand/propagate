package com.hummingbird.propagate.mapper;

import org.springframework.dao.DataAccessException;

import com.hummingbird.propagate.entity.ShareArticle;

public interface ShareArticleMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id)throws DataAccessException;

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ShareArticle record)throws DataAccessException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ShareArticle record)throws DataAccessException;

    /**
     * 根据主键查询记录
     */
    ShareArticle selectByPrimaryKey(Integer id)throws DataAccessException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ShareArticle record)throws DataAccessException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ShareArticle record)throws DataAccessException;
}