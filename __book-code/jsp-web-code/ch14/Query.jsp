<%@page contentType="text/html"%>
<%@page pageEncoding="GB2312"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>��ʾquery��ǩ</title>
</head>
<body>
<sql:setDataSource url="jdbc:mysql://localhost/liuyan"
	driver="com.mysql.jdbc.Driver" user="root" password="root" var="ds" />
	ʹ��&lt;sql:query&gt;�������м�¼<br>
<sql:query dataSource="${ds}" sql="SELECT * FROM message"
	var="selectResult" />
<c:forEach var="row" items="${selectResult.rows}">
	<c:out value="${'������'}" />
	<c:out value="${row.Name}" />
	<c:out value="${'|���⣺'}" />
	<c:out value="${row.title}" />
	<c:out value="${'|�������ݣ�'}" />
	<c:out value="${row.content}" />
	<br>
</c:forEach>
<br>������ʾ���ǰ���������
<br>
<sql:query dataSource="${ds}"
	sql="SELECT * FROM message WHERE name=? AND title=?" var="selectResult">
	<sql:param value="${'cherry'}" />
	<sql:param value="${'welcome'}" />
</sql:query>

<c:forEach var="row" items="${selectResult.rows}">
	<c:out value="${'������'}" />
	<c:out value="${row.name}" />
	<c:out value="${'|���⣺'}" />
	<c:out value="${row.title}" />
	<c:out value="${'|�������ݣ�'}" />
	<c:out value="${row.content}" />
	<br>
</c:forEach>
</body>
</html>
