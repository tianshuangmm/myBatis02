package com.ts.mybatis02.dao;

import com.ts.mybatis02.bean.Command;
import com.ts.mybatis02.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class CommandDAO {
    DBAccess dbAccess = new DBAccess();

    //查询list
    public List<Command> queryCommandList() {
        SqlSession sqlSession = null;
        List<Command> commandList = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            commandList = sqlSession.selectList("Command.queryCommandList");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return commandList;
    }

    public static void main(String[] args) {
        //一对多查询并在控制台输出结果
        CommandDAO commandDAO = new CommandDAO();
        List<Command> commandList = commandDAO.queryCommandList();
        if (commandList != null && commandList.size() > 0) {
            for (int i = 0; i < commandList.size(); i++) {
                System.out.println(commandList.get(i));
            }
        }
    }
}
