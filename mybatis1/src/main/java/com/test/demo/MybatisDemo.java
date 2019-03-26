package com.test.demo;

import com.test.mybatis.beans.User;
import com.test.mybatis.mapper.UserMapper;
import com.test.plugin.Page;
import com.test.plugin.ThreadLocalUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

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
            session=getSqlSession();
            SqlSession session2=getSqlSession();
            UserMapper  mapper= session.getMapper(UserMapper.class);
            UserMapper  mapper2= session2.getMapper(UserMapper.class);
            User user= mapper.selectByPrimaryKey("1");
            System.out.println("session1第一次查询的值："+user.getName());
           // session.clearCache();  如果不想从缓存中获取值可以 清理掉缓存再查询
            user= mapper.selectByPrimaryKey("1");
            System.out.println("session1查询到缓存的值："+user.getName());

            user= mapper2.selectByPrimaryKey("1");
            System.out.println("session2查询到的值："+user.getName());

//            user =  getSqlSession().getMapper(UserMapper.class).getMyUser();
//            System.out.println(user.getName());
//
//            Page<User> pager = new Page<User>(1, 2);
//            ThreadLocalUtil.threadLocal.set(pager);
//
//            List<User> list = getSqlSession().getMapper(UserMapper.class).selectUserByPage();
//            Page page = ThreadLocalUtil.threadLocal.get();
//            System.out.println(list.size()+page.toString());
//
//
//            User u= new User();
//            u.setId("11");
//            u.setName("name11");
//            u.setSex("1");
//            getSqlSession().getMapper(UserMapper.class).insert(user );

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
