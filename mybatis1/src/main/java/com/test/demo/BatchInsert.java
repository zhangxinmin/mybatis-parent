package com.test.demo;

import com.test.mybatis.beans.User;
import com.test.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatchInsert extends BaseDemo{
    public static void main(String[] args) throws Exception {
         //batch1();  //3249
         //batch2(); //900
         batch3();  //1305
    }
//for循环，性能差，每次都要进行db操作。
    public static void batch1() throws Exception {

        SqlSession session = getSqlSession();
        UserMapper mapper =  session.getMapper(UserMapper.class);
        Date start = new Date();
        for(int i=0;i<5000;i++){
             User u = new User();
             u.setSex("1");u.setId("1id"+i);u.setName("name"+i);
             mapper.insert(u    );
        }
        System.out.println(new Date().getTime()-start.getTime());
        session.commit();
        session.close();
    }
//foreach 拼 SQL，性能最高，但是sql长度有限制，每次执行完必须检查
// mysql默认接受sql的大小是1048576(1M)，若数据量超过1M会报异常：
// 查看mysql sql最大长度 show variables like '%packet%';show variables like '%net_buffer%';
//可调整MySQL安装目录下的my.ini文件中的max_allowed_packet选项修改支持的sql最大长度
    public static void batch2() throws Exception {

        SqlSession session = getSqlSession();
        UserMapper mapper =  session.getMapper(UserMapper.class);
        Date start = new Date();
        List list = new ArrayList();
        for(int i=0;i<5000;i++){
            User u = new User();
            u.setSex("1");u.setId("2id"+i);u.setName("name2"+i);
             list.add(u    );
        }
         mapper.insertBatch(list    );
        session.commit();
        session.close();
        System.out.println(new Date().getTime()-start.getTime());

    }
//同jdbc的批量操作
    public static void batch3() throws Exception {
        SqlSession session = getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        UserMapper mapper =  session.getMapper(UserMapper.class);
        List list = new ArrayList();
        Date start = new Date();
        for(int i=0;i<5000;i++){
            User u = new User();
            u.setSex("1");u.setId("3id3"+i);u.setName("name3"+i);
           mapper.insert(u  );
        }
        session.commit();
        session.close();
        System.out.println(new Date().getTime()-start.getTime());
    }
}
