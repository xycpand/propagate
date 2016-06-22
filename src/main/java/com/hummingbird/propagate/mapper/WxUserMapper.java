package com.hummingbird.propagate.mapper;

import com.hummingbird.propagate.entity.WxUser;

public interface WxUserMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer userid);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(WxUser record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(WxUser record);

    /**
     * 根据主键查询记录
     */
    WxUser selectByPrimaryKey(Integer userid);
    
    
    WxUser selectUserByUnionid(String unionid);
    
    /**
     * 根据openid查询用户信息
     * @param openid
     * @return
     */
    WxUser selectUserByOpendId(String openid);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(WxUser record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKeyWithBLOBs(WxUser record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(WxUser record);
}