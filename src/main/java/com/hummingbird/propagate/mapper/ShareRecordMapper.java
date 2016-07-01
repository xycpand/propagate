package com.hummingbird.propagate.mapper;

import org.apache.ibatis.annotations.Param;

import com.hummingbird.propagate.entity.ShareRecord;

public interface ShareRecordMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ShareRecord record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ShareRecord record);

    /**
     * 根据主键查询记录
     */
    ShareRecord selectByPrimaryKey(Integer id);
    
    /**
     * 根据用户id和文章id查询分享记录
     * @param userid
     * @param articleId
     * @return
     */
    ShareRecord selectByUserIdAndArticleId(@Param("userid")Integer userid,@Param("articleId")String articleId);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ShareRecord record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ShareRecord record);
}