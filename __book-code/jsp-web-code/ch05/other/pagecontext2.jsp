<%@ page language="java" contentType="text/html; charset=gb2312"%>
pageContext�Ĳ���ҳ��-���ǰһҳ�����õ�ֵ��
<br>
<%
	out.println("<br>pageContext.getAttribute('userName')=");
	out.println(pageContext.getAttribute("userName"));
	out.println("<br>pageContext.getSession().getAttribute('sessionValue')=");
	out.println(pageContext.getSession().getAttribute("sessionValue"));
	out.println("<br>pageContext.getServletContext().getAttribute('sharevalue')=");
	out.println(pageContext.getServletContext().getAttribute("sharevalue"));
%>
