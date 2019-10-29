package com.ts.mybatis02.dao;

import com.ts.mybatis02.bean.Command;
import com.ts.mybatis02.bean.Message;
import com.ts.mybatis02.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class InterfaceTest {
    //测试
    /*public static void main(String[] args) {
        MessageInterfaceMapperDao messageInterfaceMapperDao = new MessageInterfaceMapperDao();
        Message message = new Message();
        List<Message> messages = messageInterfaceMapperDao.queryMessageList(message);
        //判断不为空缺size大于0
        for (int i = 0; i < messages.size(); i++) {
            System.out.println(messages.get(i));
        }
    }*/
    public static void main(String[] args) {
        CommandInterfaceMapperDao commandInterfaceMapperDao = new CommandInterfaceMapperDao();
        List<Command> commands = commandInterfaceMapperDao.queryCommandList();
        if (commands != null && commands.size() > 0) {
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(commands.get(i));
            }
        }
    }
}
