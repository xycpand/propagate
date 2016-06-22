package com.hummingbird.propagate.mapper;

import java.util.List;

import com.hummingbird.propagate.entity.UserTag;
import com.hummingbird.propagate.vo.TagVO;

public interface UserTagMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(UserTag record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(UserTag record);

    /**
     * 根据主键查询记录
     */
    UserTag selectByPrimaryKey(Integer id);
    
    List<TagVO> queryUserTag(Integer userid);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(UserTag record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(UserTag record);
    
}