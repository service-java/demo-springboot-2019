<%@ page contentType="text/html;charset=gb2312"%>
<html>
<head>
<title>����Cookieʾ��-��ȡCookie</title>
</head>
<body>
<h2>����Cookieʾ��-��ȡCookie</h2>
<%
	// ����ǰվ�������Cookie���벢����Cookies������
	Cookie[] Cookies = request.getCookies();
	Cookie sCookie = null;
	String cookieName = null;
	String cookieValue = null;
	if (Cookies == null) // ���û���κ�Cookie
		out.print("û��Cookie");
	else {
		try {
			if (Cookies.length == 0) {
		System.out.println("�ͻ��˽�ֹд��cookie");
			} else {
		for (int i = 0; i < Cookies.length; i++) { // ѭ���г����п��õ�Cookie

			sCookie = Cookies[i];
			cookieName = sCookie.getName();
			cookieValue = sCookie.getValue();
			if (cookieName.equals("mycookie")) {
				out.println(cookieName + "->" + cookieValue
				+ "<br>");
				break;
			}
		}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
%>
</body>
</html>


