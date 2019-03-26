package com.test;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
////此处如果不用注解指定jdbcType, 那么，就可以在配置文件中通过"jdbcType"属性指定， 同理， javaType 也可通过 @MappedTypes指定
//@MappedJdbcTypes(JdbcType.VARCHAR)
//@MappedTypes(System.class)
public class MyTypeHandler extends BaseTypeHandler {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {

    }
    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getString(s)+"_fix";
    }
    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
