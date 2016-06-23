package com.hummingbird.propagate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hummingbird.propagate.entity.ArticleTag;
import com.hummingbird.propagate.vo.TagVO;

public interface ArticleTagMapper {
    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 根据文章主键和标签名称删除记录
     */
    int deleteByName(@Param("articleId")String articleId,@Param("name")String name);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(ArticleTag record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(ArticleTag record);
    /**
     * 查询文章标签
     * @param articleId
     * @return
     */
    List<TagVO> queryArticleTag(String articleId);
    /**
     * 根据主键查询记录
     */
    ArticleTag selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(ArticleTag record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(ArticleTag record);
}