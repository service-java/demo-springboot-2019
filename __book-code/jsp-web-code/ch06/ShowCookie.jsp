<%@ page contentType="text/html;charset=gb2312"%>
<html>
<head>
<title>��ʾCookie</title>
</head>
<body>
<h2> Cookie������ʾ��</h2>
<%
	//ȡ�ÿͻ��˵�����Cookie
	Cookie[] Cookies = request.getCookies();
    
	Cookie sCookie = null;
	String cookieName = null;
	String cookieValue = null;
	int cookieVersion=0;
	if (Cookies == null) // ���û���κ�Cookie
		out.print("û��Cookie");
	else {
		try {
			if (Cookies.length == 0) {
		System.out.println("�ͻ��˽�ֹд��cookie");
			} else {
				for (int i = 0; i <= Cookies.length; i++) { // ѭ���г����п��õ�Cookie
			sCookie = Cookies[i];
			cookieName = sCookie.getName();
			cookieValue = sCookie.getValue();
			cookieVersion=sCookie.getVersion();
			out.print("<P><b>Cookie�������ǣ�"+cookieName+"<br><p>");
			out.print("<P><b>Cookie�İ汾�ǣ�"+cookieVersion+"<br><p>");
			out.print("<P><b>Cookie��ֵ�ǣ�"+cookieValue+"<br><p>");
			}
		}
			}
		catch (Exception e) {
			System.out.println(e);
		}
	}
%>
</body>
</html>
