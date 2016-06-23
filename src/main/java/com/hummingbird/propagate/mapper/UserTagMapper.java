package com.hummingbird.propagate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
     * 根据用户id和标签id查询用户标签失败
     * @param userid
     * @param tagId
     * @return
     */
    UserTag queryUserTagByUserIdAndTagId(@Param("userid")Integer userid,@Param("tagId") Integer tagId);

    /**
     * 根据主键查询记录
     */
    UserTag selectByPrimaryKey(Integer id);
    
    List<UserTag> queryUserTagByName(String name);
    
    List<String> queryUserTag(@Param("userId")Integer userId);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(UserTag record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(UserTag record);
    
}