<%@ page language="java" contentType="text/html; charset=GBK" import="com.*"%>
<html>
<head>
<title>��EL����JavaBean�е�����</title>
</head>
<body>
<%
	ELJavaBean id= new ELJavaBean("20130409");
	session.setAttribute("PID",id);	
%>
��EL����JavaBean��personID���Ե�ֵ: 
${sessionScope.PID.personID }
</body>
</html>