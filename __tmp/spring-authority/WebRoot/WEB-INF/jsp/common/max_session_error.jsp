<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<center>
    <div style="width: 100%;text-align: center;font-size: 18px;font-weight: bolder;">
                    您的账号已在别的地方登录，请重新登录，确保您的账号安全<a href="home">重新登录</a>
    </div>
</center>