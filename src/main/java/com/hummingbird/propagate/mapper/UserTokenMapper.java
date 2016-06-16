package com.hummingbird.propagate.mapper;

import com.hummingbird.commonbiz.vo.UserToken;
import com.hummingbird.propagate.entity.Token;

public interface UserTokenMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(String token);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Token record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Token record);

    /**
     * 根据主键查询记录
     */
    Token selectByPrimaryKey(String token);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Token record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Token record);
    
    /**
     * 根据条件获取token
     * @param record
     * @return
     */
    Token selectByToken(UserToken record);
    
    /**
     * 根据条件获取token
     * @param record
     * @return
     */
    Token selectByTokenStr(String token);
}