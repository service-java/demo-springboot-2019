<%@page language="java" contentType="text/html;charset=gb2312"
	import="java.util.*"%>
<HTML>
<HEAD>
<TITLE>responseӦ��ʵ��</TITLE>
</HEAD>
<BODY>
<%
	out.println(new Date().toLocaleString()); //��õ�ǰʱ��
	response.setHeader("refresh", "1"); //����ÿ1��ˢ��һ��ˢ��
%>
</BODY>
</HTML>
