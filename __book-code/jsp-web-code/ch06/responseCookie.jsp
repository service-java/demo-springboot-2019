<%@page contentType="text/html; charset=gb2312"%>
<%@page pageEncoding="GB2312"%>
<html>
<head>
<title>��ȡCookie����</title>
</head>
<body>
<%
	Cookie cookies[] = request.getCookies();
	int count = cookies.length;
	String name = "", sex = "", color = "";

	for (int i = 0; i < count; i++)
		if (cookies[i].getName().equals("name"))
			name = cookies[i].getValue();
		else if (cookies[i].getName().equals("sex"))
			sex = cookies[i].getValue();
		else if (cookies[i].getName().equals("color"))
			color = cookies[i].getValue();
%>
<font color="<%=color%>" size="5"><%=java.net.URLDecoder.decode(name)%></font>
���ã����������ĸ������ϡ�
<p>
<%
	out.println("�Ա�<br>");
	if (sex.equals("M"))
		out.println("<img src = 'boy.jpg'>��������..<p>");
	else
		out.println("<img src = 'girl.jpg'>����Ů��..<p>");
%>

</body>
</html>
