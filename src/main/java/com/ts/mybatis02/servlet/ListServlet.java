package com.ts.mybatis02.servlet;

import com.ts.mybatis02.bean.Message;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//不分层jdbc连接数据库信息
//如果用注解@WebServlet可以不web.xml直接使用，注解@webServlet如下
//@WebServlet(name="/ListTwoServlet",urlPatterns="/ListTwoServlet")
//注意Servlet的生命周期，只实例化一次，所以必须要重启才能生效。但是debug模式下eclipse会为servlet重新加载
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp)
        //设置编码//不加这句的话，在查询框里输入中文，下面getParameter得到的值会是乱码
        req.setCharacterEncoding("utf-8");
        //获取前台传递的参数
        String command = null;
        String content = null;
        if (req.getParameter("command") != null) {
            command = req.getParameter("command").trim();//和jsp中查询框input的name匹配
        }
        if (req.getParameter("content") != null) {
            content = req.getParameter("content").trim();
        }
        //页面回显集合
        List<Message> list = new ArrayList<Message>();
        //statement参数集合
        List<String> paramList = new ArrayList<String>();
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.创建连接对象
            //mysql返回的时间总是有问题，比实际时间要早8小时
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis01?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "123456");

            //拼接sql
            StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from MESSAGE where 1=1 ");//这里不* ，*会影响查询效率
            if (command != null && !"".equals(command)) {
                /* sql.append(" and command = '"+command+"'");*/
                sql.append(" and command = ?");//注意这里有个空格，否则拼接完成后有语法错误。
                paramList.add(command);
                req.setAttribute("command", command);
            }
            if (content != null && !"".equals(content)) {
                /*sql.append(" and content like '%"+content+"%'");*/
                sql.append(" and content like '%' ? '%'");//mysql的模糊查询格式
                paramList.add(content);
                req.setAttribute("content", content);
            }

            //打印sql拼接情况
            System.out.println(sql.toString());
            //3.创建properStatement对象

            PreparedStatement statement = connection.prepareStatement(sql.toString());
            //向statement里面设置参数

            for (int i = 0; i < paramList.size(); i++) {
                statement.setString(i + 1, paramList.get(i));//sql中?的位置从1开始
            }

            //4.执行查询
            ResultSet resultSet = statement.executeQuery();

            //5.封装结果集
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getString("ID"));
                message.setCommand(resultSet.getString("COMMAND"));
                message.setContent(resultSet.getString("CONTENT"));
                message.setDescription(resultSet.getString("DESCRIPTION"));
                list.add(message);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //返回数据
        req.setAttribute("messageList", list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        //页面跳转
        //注意jsp文件放在WEB-INF下，这样必须经过后台才能访问到jsp。
        //如果放在WebRoot下（和WEB-INF平级）是能够被直接访问到的，除非设置了jsp拦截。
        req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
