<%@ page language="java" contentType="text/html; charset=gb2312"%>
<HTML>
<HEAD>
<TITLE>��ȡapplicationֵ</TITLE>
</HEAD>
<BODY>
<%
	Object id = application.getAttribute("name");
	Object sex = application.getAttribute("sex");

	if (id != null) {
		out.println("������" + id.toString());
		out.println("<br>");
		out.println("�Ա�" + sex.toString());

	} else {
		out.println("������application���� !!");
	}
%>
</BODY>
</HTML>
