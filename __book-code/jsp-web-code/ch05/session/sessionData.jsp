<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%
request.setCharacterEncoding("gb2312");
%>
<html>
<head>
<title>���� session ����</title>
</head>
<body>
<%
	String name = request.getParameter("name");
	String sex = request.getParameter("sex");

	session.setAttribute("name", name);
	session.setAttribute("sex", sex);
%>
<a href="usingSession.jsp"> ��ʾ�����õ�session �������� </a>
</body>
</html>
