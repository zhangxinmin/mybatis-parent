package com.test.mybatis.mapper;

import com.test.mybatis.beans.Post;
import com.test.mybatis.beans.PostExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    int countByExample(PostExample example);

    int deleteByExample(PostExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Post record);

    int insertSelective(Post record);

    List<Post> selectByExample(PostExample example);

    Post selectByPrimaryKey(Integer pid);
    Post getPost(Integer pid);
    Post getPost2(Integer pid);
    int updateByExampleSelective(@Param("record") Post record, @Param("example") PostExample example);

    int updateByExample(@Param("record") Post record, @Param("example") PostExample example);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
}