<%@ page contentType="text/html;charset=GB2312"%>
<html>
<head>
<title>session����</title>
</head>
<body>
<h2>session�����ڶ���ҳ��</h2>
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
</body>
</html>
