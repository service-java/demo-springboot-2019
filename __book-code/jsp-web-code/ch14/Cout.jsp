<%@ page contentType="text/html;charset=GBk" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>JSTL -- c:out </title>
</head>
<body bgcolor="#FFFFFF"><h1>
&lt;c:out&gt;
<%
pageContext.setAttribute("myVar", "���ԣ�ҳ����Ч");
request.setAttribute("myVar", "���ԣ�������Ч");
session.setAttribute("myVar", "���ԣ��Ự��Ч");
java.io.Reader reader1 = new java.io.StringReader("<h2>���������ַ����ı�</h2>");
pageContext.setAttribute("myReader1", reader1);
java.io.Reader reader2 = new java.io.StringReader("<font color=red>���������ַ����ı�</font>");
pageContext.setAttribute("myReader2", reader2);
%>
<c:out value="�����ַ������:"/><c:out value="֣�� 2013"/><br/>
<c:out value="���ʽ�����"/><c:out value="${2013+100}"/><br/>
<c:out value="Ĭ��ֵ�����"/>
<c:out value="${param.name}" default="û������name����"/><br/>
<c:out value="�����������:"/><c:out value="${myVar}"/><br/>

<c:out value="�����ַ������"/><br/>
<!-- ������������� -->
(escapeXml=true) : <c:out value="${myReader1}"/><br/>
<!-- ���������ɫ�ַ��� -->
(escapeXml=false): <c:out value="${myReader2}" escapeXml="false"/><br/>
<h1>
</body>
</html>