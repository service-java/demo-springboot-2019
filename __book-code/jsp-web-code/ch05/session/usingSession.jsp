<%@ page language="java" contentType="text/html; charset=gb2312"%>
<HTML>
<HEAD>
<TITLE>��ȡsessionֵ</TITLE>
</HEAD>
<BODY>
<%
	Object id = session.getAttribute("name");
	Object sex = session.getAttribute("sex");

	if (id != null) {
		out.println("������" + id.toString());
		out.println("<br>");
		out.println("�Ա�" + sex.toString());

	} else {
		out.println("������seeion���� !!");
	}
%>
</BODY>
</HTML>
