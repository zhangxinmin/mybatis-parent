package com.test.demo;

import com.test.mybatis.beans.User;
import com.test.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MybatisDemo {
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSessionFactory getSqlSessionFactory() throws FileNotFoundException {
        if(null!=sqlSessionFactory){
            return  sqlSessionFactory;
        }
        InputStream configFile = new FileInputStream("D:\\works\\work3\\mybatis\\mybatis1\\src\\main\\resources\\xml\\mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFile);
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() throws FileNotFoundException {
        SqlSession session =getSqlSessionFactory().openSession();
        return session;
    }

    public static void main(String[] args) {
        SqlSession session=null;
        try {
            User user= getSqlSession().getMapper(UserMapper.class).selectByPrimaryKey("1");
            System.out.println(user.getName());
            user =  getSqlSession().getMapper(UserMapper.class).getMyUser();
            System.out.println(user.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(null!=session)
                 session.close();
        }

    }
}
