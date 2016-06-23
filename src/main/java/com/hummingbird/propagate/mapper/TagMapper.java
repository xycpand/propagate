package com.hummingbird.propagate.mapper;

import java.util.List;

import com.hummingbird.propagate.entity.Tag;
import com.hummingbird.propagate.vo.TagVO;

public interface TagMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(Tag record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(Tag record);

    /**
     * 根据主键查询记录
     */
    Tag selectByPrimaryKey(Integer id);
    
    /**
     * 根据名称查询标签
     * @param name
     * @return
     */
    
    List<Tag> queryTagByName(String name);
    
    List<String> queryHotTag(Integer num);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(Tag record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(Tag record);
}