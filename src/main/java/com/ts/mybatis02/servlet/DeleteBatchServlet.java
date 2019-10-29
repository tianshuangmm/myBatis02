package com.ts.mybatis02.servlet;

import com.ts.mybatis02.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteBatchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        //获取参数
        String[] ids = request.getParameterValues("id");

        //数组转集合
        List<String> list = Arrays.asList(ids);

        //遍历显示
        for (int i = 0; i < list.size(); i++) {
            System.out.println("批量删除id" + i + "为:" + list.get(i));
        }
        MessageService messageService = new MessageService();
        messageService.deleteByListId(list);

        //重定向
        response.sendRedirect("/mybatis/ListServlet2");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
