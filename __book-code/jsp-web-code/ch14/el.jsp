<%@ page language="java" contentType="text/html; charset=GB2312"%>
<html>
  <head>
    <title>EL ��������</title>
  </head>
  <body>
  
   <form method="GET" action="InternalObject.jsp">
    <input type="text" name="name" size="20"><br>
    <input type="submit" value="ȷ��" >
    <input type="reset" value="ȡ��" >
   </form>  
 <%
            Cookie nameCookie = new Cookie("cname", "JSP");           
            response.addCookie(nameCookie);           
  %>
  </body>
</html>