<%@ page contentType="text/html;charset=gb2312"%>
<html>
<head>
<title>����Cookieʾ��</title>
</head>
<body>
<h2>Cookieʾ��</h2>
<%
//ȡ�ô�������ֲ���
String name=(request.getParameter("name")!=null)?request.getParameter("name"):"jack";	
//����Cookie
Cookie cookie=new Cookie("name",name);
//����Cookie�ı���ʱ��
cookie.setMaxAge(6000);
//�ڿͻ��˱���cookie
response.addCookie(cookie);
%>
<a href="ShowCookie.jsp">��ʾCookieֵ</a>
</body>
</html>
