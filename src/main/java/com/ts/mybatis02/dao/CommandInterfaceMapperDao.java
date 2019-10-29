package com.ts.mybatis02.dao;

import com.ts.mybatis02.bean.Command;
import com.ts.mybatis02.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class CommandInterfaceMapperDao {
    List<Command> queryCommandList() {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        List<Command> commands = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            CommandInterfaceMapper mapper = sqlSession.getMapper(CommandInterfaceMapper.class);
            commands = mapper.queryCommandList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commands;
    }
}
