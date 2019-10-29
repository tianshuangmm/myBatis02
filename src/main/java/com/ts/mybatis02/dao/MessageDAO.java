package com.ts.mybatis02.dao;

import com.ts.mybatis02.bean.Message;
import com.ts.mybatis02.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class MessageDAO {
    DBAccess dBaccess = new DBAccess();

    //查询消息
    public List<Message> queryMessageList(Message message) {
        SqlSession sqlSession = null;
        List<Message> messageList = null;
        try {
            sqlSession = dBaccess.getSqlSession();
            //第一个参数，"Message.queryMessageList"为MessageMapper.xml中sql语句的namespa.id
            messageList = sqlSession.selectList("Message.queryMessageList", message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return messageList;
    }

    //删除消息根据id实际开发中不会删除，都是该状态值
    public void deleteById(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = dBaccess.getSqlSession();
            sqlSession.delete("Message.delete", id);
            sqlSession.commit();//提交  添加修改删除需要提交
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    //批量删除
    public void deleteByListId(List list) {
        SqlSession sqlSession = null;
        try {
            sqlSession = dBaccess.getSqlSession();
            sqlSession.delete("Message.deleteBatch", list);
            sqlSession.commit();//提交  添加修改删除需要提交
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    //自增主键回显
    public void InsertMessage(Message message) {
        SqlSession sqlSession = null;
        try {
            sqlSession = dBaccess.getSqlSession();
            sqlSession.delete("Message.insert", message);
            sqlSession.commit();//提交  添加修改删除需要提交
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    //测试
    public static void main(String[] args) {
        //测试查询自增主键回显
        MessageDAO messageDAO = new MessageDAO();
        Message message = new Message();
        message.setCommand("使用useGeneratedKey添加");
        message.setDescription("添加一条数据");
        message.setContent("id设置为自增");
        /*
        //测试sqlSession
        List<Message> messageList =messageDAO.queryMessageList(message);
        if(messageList!=null&&messageList.size()>0){
            if(messageList!=null&&messageList.size()>0){
                for (int i = 0; i <messageList.size() ; i++) {
                    System.out.println(messageList.get(i));
                }
            }
        }*/
        messageDAO.InsertMessage(message);
    }
}
