<%@page contentType="text/html"%>
<%@page pageEncoding="GB2312"%>
<html>
    <head><title>JSP Page</title></head>
    <body>
    <%
        //request.setCharacterEncoding("GB2312") ; 
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
    %>
    Hi , <%=name%> ���� : <br>   <br>
    ������ĸ�����������<p>
    ������<%=name%><br>
    E-mail��<%=email%><br>
    �绰��<%=tel%>
    </body>
</html>
