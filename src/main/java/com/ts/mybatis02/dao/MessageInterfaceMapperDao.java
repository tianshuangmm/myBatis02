package com.ts.mybatis02.dao;

import com.ts.mybatis02.bean.Message;
import com.ts.mybatis02.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

//接口式编程
public class MessageInterfaceMapperDao {
    List<Message> queryMessageList(Message message) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        List<Message> messages = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            MessageInterfaceMapper mapper = sqlSession.getMapper(MessageInterfaceMapper.class);
            messages = mapper.queryMessageList(message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return messages;
    }
}
