package com.ts.mybatis02.servlet;

import com.ts.mybatis02.bean.Message;
import com.ts.mybatis02.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//删除message根据id
public class DeleteOneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        //获取删除的id
        String id = request.getParameter("id");
        System.out.println("要删除的id为:" + id);
        Message message = new Message();
        message.setId(id);

        //调用删除业务逻辑
        MessageService messageService = new MessageService();

        //删除可以只传id，传message对象也可以，数据库String，int 参数传递可以均为String
        messageService.deleteById(id);

        //重定向  request,response对象已经销毁
        response.sendRedirect("/mybatis/ListServlet2");
        //this.getServletContext().getRequestDispatcher("/ListServlet2");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
