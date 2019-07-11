<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
 	 <jsp:useBean id="loginBean" class="lyons.entity.Login" scope="session"/>
 
 	 <% request.setCharacterEncoding("UTF-8"); %>
 		<ul class="user">
    			<li>
    				<%
    				    String str = null;
    				    str = loginBean.getUsername();
    					if(str.equals("userNull")||str==null)
    					{
    					   HttpSession s= request.getSession(true);
                           s.invalidate();
                        %>
	     					<a href="jsp/join/login.jsp">登录</a>or<a href="jsp/join/register.jsp">注册</a>
    					<%
    					   return;
    					}
    				 %>
   						<dl>
   							<dt>
	    						<a>欢迎您,<b><font color="red"><%= str %></font></b></a>
	    						<a href="<%= path %>/lyons.control/HandleExit"><font color="#CDC9C9">退出</font></a>
   							</dt>
   						</dl>
    			</li>
    		</ul>
    		
    		<% 
                String value = null;
                value = request.getParameter("first");
                if(value!=null && "FP".equals(value))
                {%>
                    <center>
                    <br><br><br>
                        <img src="image/page/first.jpg" width=700px height=500px /> 
                    </center>
                <%}
            %>
            
  </body>
</html>
