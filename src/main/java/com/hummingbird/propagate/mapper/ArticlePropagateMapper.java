package com.hummingbird.propagate.mapper;

import org.apache.ibatis.annotations.Param;

import com.hummingbird.propagate.entity.ArticlePropagate;

public interface ArticlePropagateMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ArticlePropagate record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ArticlePropagate record);

    /**
     * 根据主键查询记录
     */
    ArticlePropagate selectByPrimaryKey(Integer id);

    
    ArticlePropagate  selectByUserIdAndArticleId(@Param("userid")Integer userid,
    		@Param("articleId")String articleId);
    
    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ArticlePropagate record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKeyWithBLOBs(ArticlePropagate record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ArticlePropagate record);
}