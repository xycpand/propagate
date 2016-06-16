package com.hummingbird.propagate.mapper;

import com.hummingbird.propagate.entity.UserRecord;

public interface UserRecordMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(UserRecord record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(UserRecord record);

    /**
     * 根据主键查询记录
     */
    UserRecord selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(UserRecord record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(UserRecord record);
}