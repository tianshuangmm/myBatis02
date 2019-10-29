package com.ts.mybatis02.service;

import com.ts.mybatis02.bean.Message;
import com.ts.mybatis02.dao.MessageDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO = new MessageDAO();

    public List<Message> queryMessageList(Message message) {
        //调用数据访问层方法并处理业务逻辑
        List<Message> messageList = messageDAO.queryMessageList(message);
        return messageList;
    }

    public void deleteById(String id) {
        messageDAO.deleteById(id);
    }

    public void deleteByListId(List list) {
        messageDAO.deleteByListId(list);
    }
}
