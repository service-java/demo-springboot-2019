<%@ page contentType="text/html; charset=gb2312" language="java"%>
<html>
<head>
<title>Includeָ��ʾ��</title>
</head>
<body>
Ҳ��������file��ָ�����ļ�������κβ�������������д����ǲ��Ϸ��ġ�
<%@ include file="setInfo.jsp?name=browser"%>

<p>����HTML�ļ���<%@ include file="include_html.html"%>
<p>�����ı��ļ���<%@ include file="include_txt.txt"%>
<p>����JSP�ļ�,��ʾ���ڵ�����ʱ�䣺<%@include file="include_jsp.jsp"%>
<p>����JSP���룺<%@ include file="include_code.cod"%>
</body>
</html>
