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

	application.setAttribute("name", name);
	application.setAttribute("sex", sex);
%>
<a href="usingApplication.jsp"> ��ʾ�����õ�application �������� </a>
</body>
</html>
