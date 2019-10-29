package com.ts.mybatis02.servlet;

import com.ts.mybatis02.bean.Message;
import com.ts.mybatis02.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//分层mybatis 查询回显数据
public class ListServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式避免乱码
        request.setCharacterEncoding("utf-8");
        //获取数据
        String command = null;
        String content = null;
        if (request.getParameter("command") != null) {
            command = request.getParameter("command").trim();//和jsp中查询框input的name匹配
            System.out.println(command);
        }
        if (request.getParameter("content") != null) {
            content = request.getParameter("content").trim();
        }
        //mybatis 只允许传递一个参数
        Message message = new Message();
        if (command != null && !"".equals(command))
            message.setCommand(command);
        if (content != null && !"".equals(content))
            message.setContent(content);

        //调用业务层方法
        MessageService messageService = new MessageService();
        List<Message> messageList = messageService.queryMessageList(message);
        if (messageList != null && messageList.size() > 0) {
            for (int i = 0; i < messageList.size(); i++) {
                System.out.println(messageList.get(i));
            }
        }

        //回显数据
        request.setAttribute("command", command);
        request.setAttribute("content", content);
        request.setAttribute("messageList", messageList);

        //请求转发
        //getRequestDispatcher是服务器内部跳转，地址栏信息不变，只能跳转到web应用内的网页。
        //sendRedirect是页面重定向，地址栏信息改变，可以跳转到任意网页。
        request.getRequestDispatcher("WEB-INF/jsp/back/list2.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
