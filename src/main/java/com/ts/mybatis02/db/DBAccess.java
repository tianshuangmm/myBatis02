package com.ts.mybatis02.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.ResourceBundle;

/*
 * sqlSession的作用
 *  向sql语句传入参数
 *  执行sql语句
 *  获取执行sql语句的结果
 *  事务的控制
 * 如何获取sqlSession
 *   通过配置文件获取数据库连接相关信息
 *   通过配置信息构建sqlSessionFactory
 *   通过sqlSessionFactory打开数据库会话
 */
//访问数据库类
public class DBAccess {

    public SqlSession getSqlSession() throws IOException {
        SqlSession sqlSession = null;
        Connection connection = null;

        //通过配置文件获取数据库连接信息
        //src是根路径
        Reader resourceAsReader = Resources.getResourceAsReader("resources/Configuration.xml");

        //通过配置信息构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);

        //通过sqlSessionFactory打开一个会话
        sqlSession = sqlSessionFactory.openSession();
        /*connection = sqlSession.getConnection();*/
        return sqlSession;
    }
}
