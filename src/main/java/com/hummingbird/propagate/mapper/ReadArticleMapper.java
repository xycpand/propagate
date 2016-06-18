package com.hummingbird.propagate.mapper;

import org.springframework.dao.DataAccessException;

import com.hummingbird.propagate.entity.ReadArticle;

public interface ReadArticleMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id)throws DataAccessException;

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ReadArticle record)throws DataAccessException;

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ReadArticle record)throws DataAccessException;

    /**
     * 根据主键查询记录
     */
    ReadArticle selectByPrimaryKey(Integer id)throws DataAccessException;

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ReadArticle record)throws DataAccessException;

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ReadArticle record)throws DataAccessException;
}