<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + path;
%>
<script src="/mybatis/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    function deleteBatch(basePath) {
        $("#mainForm").attr("action", basePath + "/mybatis/DeleteBatchServlet.action")
        $("#mainForm").submit();
    }
</script>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
    <title>内容列表页面</title>
    <link href="css/all.css" rel="stylesheet" type="text/css"/>
</head>
<body style="background: #e1e9eb;">
<form action="${basePath}/mybatis/ListServlet2" id="mainForm" method="post">
    <div class="right">
        <div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a> &gt; 内容列表</div>
        <div class="rightCont">
            <p class="g_title fix">内容列表 <a class="btn03" href="#">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03"
                                                                                                    href="javascript:deleteBatch('${basePath}')">删
                除</a></p>
            <table class="tab1">
                <tbody>
                <tr>
                    <td width="90" align="right">指令名称：</td>
                    <td>
                        <input name="command" type="text" class="allInput" value="${command}"/>
                    </td>
                    <td width="90" align="right">内容：</td>
                    <td>
                        <input name="content" type="text" class="allInput" value="${content}"/>
                    </td>
                    <td width="85" align="right"><input type="submit" class="tabSub" value="查 询"/></td>
                </tr>
                </tbody>
            </table>
            <div class="zixun fix">
                <table class="tab2" width="100%">
                    <tbody>
                    <tr>
                        <td><input type="checkbox" id="all" onclick=""/></td>
                        <td>序号</td>
                        <td>指令名称</td>
                        <td>内容</td>
                        <td>操作</td>
                    </tr>
                    <c:forEach items="${messageList}" var="message" varStatus="status">
                        <tr>
                            <td><input type="checkbox" name="id" value="${message.id }"/></td>
                                <%--<td><input type="checkbox" id="" onclick=""/></td>--%>
                            <td>${status.index+1}</td>
                            <td>${message.command}</td>
                                <%--<td>${description}</td>--%>
                            <td>${message.content}</td>
                            <td>
                                <a href="${basePath}/mybatis/DeleteOneServlet?id=${message.id}">删除</a>&nbsp;&nbsp;&nbsp;
                                <a href="#">修改</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class='page fix'>
                    共 <b>4</b> 条
                    <a href='###' class='first'>首页</a>
                    <a href='###' class='pre'>上一页</a>
                    当前第<span>1/1</span>页
                    <a href='###' class='next'>下一页</a>
                    <a href='###' class='last'>末页</a>
                    跳至&nbsp;<input type='text' value='1' class='allInput w28'/>&nbsp;页&nbsp;
                    <a href='###' class='go'>GO</a>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
