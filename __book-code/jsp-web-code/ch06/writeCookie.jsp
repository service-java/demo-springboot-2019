<%@ page contentType="text/html;charset=gb2312"%>
<html>
<head>
<title>����Cookieʾ����д��Cookie</title>
</head>
<body>
<h2>����Cookieʾ��-д��Cookie</h2>
<%
		try {
		Cookie _Cookie = new Cookie("mycookie", "COOKIE_TEST");
		_Cookie.setMaxAge(10 * 60); // ����Cookie�Ĵ��ʱ��Ϊ10����
		response.addCookie(_Cookie); // д��ͻ���Ӳ��
		out.print("�Ѿ���Cookieд��ͻ���");
	} catch (Exception e) {
		System.out.println(e);
	}
%>
</body>
</html>
