package com.test.demo;

import com.test.mybatis.beans.Blog;
import com.test.mybatis.beans.Post;
import com.test.mybatis.mapper.BlogMapper;
import com.test.mybatis.mapper.PostMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.FileNotFoundException;

public class UnionResult extends BaseDemo {
    public static void main(String[] args) throws FileNotFoundException {
        SqlSession session = getSqlSession();
//        PostMapper mapper = session.getMapper(PostMapper.class);
//        Post post =mapper.getPost(1);
//        System.out.println(post.getBlog().getName());
//        Post post2 =mapper.getPost2(1);
//        System.out.println(post2.getPostName());
//        System.out.println(post2.getBlog().getName());


        BlogMapper bmapper = session.getMapper(BlogMapper.class);
//        Blog b = bmapper.getBlog(1);
//        System.out.println(b.getName());
//        System.out.println(b.getPosts().size());

        Blog b2 = bmapper.getBlog2(1);
        System.out.println(b2.getName());
        //System.out.println(b2.getPosts().size());
        session.close();


    }
}
