package com.ts.mybatis02.dao;

import com.ts.mybatis02.bean.Command;

import java.util.List;

public interface CommandInterfaceMapper {
    List<Command> queryCommandList();
}
