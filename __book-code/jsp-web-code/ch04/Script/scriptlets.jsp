<%@ page contentType="text/html; charset=GBK"%>
<%@ page language="java" import="java.util.*"%>
<html>
<head>
<title>��Ͼ�̬HTML�����Scriptlet</title>
</head>
<body>
<%
if (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM) {
%>
���Ϻã�
<%
} else {
%>
����ã�
<%
}
%>
</body>
</html>
