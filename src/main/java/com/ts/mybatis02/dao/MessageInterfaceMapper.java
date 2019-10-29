package com.ts.mybatis02.dao;

import com.ts.mybatis02.bean.Message;

import java.util.List;

public interface MessageInterfaceMapper {
    List<Message> queryMessageList(Message message);
}
