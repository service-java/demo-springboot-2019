<%@page contentType="text/html"%>
<%@page pageEncoding="GB2312"%>
<html>
<head>
<title>����session����</title>
</head>
<body>
<h2>session������һ��ҳ��</h2>
<%
	session.setAttribute("user1", "sessionTest1");
	session.setAttribute("user2", "sessionTest2");
%>
<table border=1>
	<tr>
		<th align="left">session�Ľ���ʱ��</th>
		<td><%=session.getCreationTime()%></td>
	</tr>
	<tr>
		<th align="left">session�ı�ʶ����</th>
		<td><%=session.getId()%><br>
		</td>
	</tr>
	<tr>
		<th align="left">session��������ʱ��</th>
		<td><%=session.getLastAccessedTime()%></td>
	</tr>
	<tr>
		<th align="left">sessionԤ�������ʱ��</th>
		<td><%=session.getMaxInactiveInterval()%></td>
	</tr>
	<%
	    session.setMaxInactiveInterval(session.getMaxInactiveInterval() + 100);
	%>
	<tr>
		<th align="left">session�µ���Чʱ��</th>
		<td><%=session.getMaxInactiveInterval()%></td>
	</tr>
	<tr>
		<th align="left">�Ƿ�Ϊ�½���session</th>
		<td><%=session.isNew()%></td>
	</tr>
	<tr>
		<th align="left">session1����ȡֵ</th>
		<td><%=session.getAttribute("user1")%></td>
	</tr>
	<tr>
		<th align="left">session2����ȡֵ</th>
		<td><%=session.getAttribute("user2")%></td>
	</tr>
</table>
<%
	session.removeAttribute("user1");
	//session.invalidate();
%>
<p><a href="sessionMethodInfo.jsp">ת����һҳ</a>
</body>
</html>
