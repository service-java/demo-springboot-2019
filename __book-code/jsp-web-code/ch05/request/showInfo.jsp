<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<%
request.setCharacterEncoding("gb2312");
%>
<html>
<head>
<title>ʹ��Request����</title>
</head>
<body bgcolor="#ccffcc">
<h1>���ղ�����������ǣ�<BR>
</h1>
<%
	Enumeration enu = request.getParameterNames();
	while (enu.hasMoreElements()) {
		String parameterName = (String) enu.nextElement();
		String parameterValue = (String) request
		.getParameter(parameterName);
		out.print("�������ƣ�" + parameterName + "<BR>");
		out.print("�������ݣ�" + parameterValue + "<BR>");
	}
%>
</body>
</html>
