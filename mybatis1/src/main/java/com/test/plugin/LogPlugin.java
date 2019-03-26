package com.test.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts(value={
        @Signature(
                type = Executor.class, // 只能是: StatementHandler | ParameterHandler | ResultSetHandler | Executor 类或者子类
                method = "query", // 表示：拦截Executor的query方法
                args = {  // query 有很多的重载方法，需要通过方法签名来指定具体拦截的是那个方法
                        MappedStatement.class,
                        Object.class,
                        RowBounds.class,
                        ResultHandler.class
                }
                /**
                 * type：标记需要拦截的类
                 * method: 标记是拦截类的那个方法
                 * args: 标记拦截类方法的具体那个引用（尤其是重载时）
                */
        )})
public class LogPlugin implements Interceptor {
    private String logName;

    /**
     * 具体拦截的实现逻辑
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("----------- intercept query start.... ---------"+logName);

        // 调用方法，实际上就是拦截的方法
        Object result = invocation.proceed();

        System.out.println("----------- intercept query end.... ---------"+logName);

        return result;
    }

    // 插入插件
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this); // 调用Plugin工具类，创建当前的类的代理类
    }

    // 设置插件属性
    @Override
    public void setProperties(Properties properties) {
        this.logName=properties.getProperty("logName");
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }
}