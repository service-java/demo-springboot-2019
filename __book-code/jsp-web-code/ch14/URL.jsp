<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>Insert title here</title>
</head>
<body>
 <h3>&lt;c:url&gt;</h3>
    <font size=-1>�ڽ�����Ƶ���Ӧ�ĳ������ϣ������״̬���п��Կ��������</font><p/>
    <b>c:url Ƕ��html����У�</b>
    <a href="<c:url value="/Cout.jsp" />">&lt;c:url&gt;���÷�</a>
    <br/>�����http://localhost:8080/ch14/Cout.jsp<p/>
    <b>ʹ��var���Ա���URL��</b>
    <c:url var="url1" value="/Cout.jsp"/>
    <a href="${url1}">&lt;c:url&gt;���÷�</a>
    <br/>�����http://localhost:8080/ch14/Cout.jsp<p/>
    <b>ʹ������WebӦ��URL��</b>
    <c:url var="examples" value="/index.html" context="/root"/>
    <a href="${examples}">Tomcatʵ��</a>
    <br/>�����http://localhost:8080/root/index.html<p/>
    <b>ʹ�ò�����</b>
    <c:url value="/Cout.jsp" var="url1">
	   <c:param name="Id" value="12345678"/>
	   <c:param name="Type" value="String"/>
	</c:url>
	<a href="${url1}">��������URL</a>
    <br/>�����http://localhost:8080/ch14/Count?Id=123456789&Type=String<p/>
    <b>ʹ�þ���URL��</b>
    <c:url var="ftp1" value="ftp://ftp.zzu.edu.cn"/>
    <a href="${ftp1}">����URL</a>
    <br/>�����ftp://ftp.zzu.edu.cn
   

</body>
</html>