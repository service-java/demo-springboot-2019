<%--
  Created by IntelliJ IDEA.
  User: Wang Genshen
  Date: 2017-09-12
  Time: 08:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>管理员登录</title>
</head>
<body>
    <form id="admin-login">
        <input type="text" name="loginVO.account" placeholder="账户名/邮箱/手机号" />
        <input type="password" name="loginVO.password" placeholder="登录密码" />
        <input type="button" value="登录" onclick="login();"/>
    </form>
</body>
<script src="<%=path %>/static/plugins/jquery3.x/jquery.min.js"></script>
<script src="<%=path %>/static/js/main.js"></script>
<script src="<%=path %>/static/js/admin.js"></script>
</html>
