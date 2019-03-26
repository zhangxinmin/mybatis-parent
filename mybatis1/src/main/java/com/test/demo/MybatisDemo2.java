package com.test.demo;

import com.test.mybatis.beans.User;
import com.test.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MybatisDemo2 {
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
            session=getSqlSession();
            UserMapper  mapper= session.getMapper(UserMapper.class);
            User user= mapper.selectByPrimaryKey("1");
            System.out.println(user.getName());
            User u2= new User();u2.setId("2");u2.setSex(user.getSex());
            u2.setName("username24");
            mapper.updateByPrimaryKey(u2);

            user= mapper.selectByPrimaryKey("1");
            System.out.println(user.getName());
            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }finally {
            if(null!=session)
                 session.close();
        }

    }
}
