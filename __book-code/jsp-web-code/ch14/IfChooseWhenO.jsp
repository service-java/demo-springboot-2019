<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="GB2312" import="java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<title>c:if c:choose c:when c:otherwise��ʹ��</title>
</head>
<body>
<h4>���ݵ�ǰʱ���������ͬ���ʺ���
	<%
	Calendar rightNow = Calendar.getInstance();
	Integer Hour=new Integer(rightNow.get(Calendar.HOUR_OF_DAY));
	request.setAttribute("hour", Hour);
	%>
	<br>&ltc:if&gtʹ�ã�  
	<c:if test="${hour >= 0 && hour <=11}">
        <c:set var="sayHello" value="����ã�"/>
    </c:if>
    <c:if test="${hour >= 12 && hour <=17}">
        <c:set var="sayHello" value="����ã�"/>
    </c:if>
    <c:if test="${hour >= 18 && hour <=23}">
        <c:set var="sayHello" value="���Ϻã�"/>
    </c:if>
     <c:out value="${sayHello}"/>    
     <br>&ltc:choose&gtʹ�ã�  
	<c:choose>
		<c:when test="${hour >= 0 && hour <=11}">
		    <c:set var="sayHello" value="����ã�"/>
		</c:when>
		<c:when test="${hour >= 12 && hour <=17}">
		    <c:set var="sayHello" value="����ã�"/>
		</c:when>
		<c:otherwise>
		    <c:set var="sayHello" value="���Ϻã�"/>
		</c:otherwise>
	</c:choose>    
    <br><c:out value="����ʱ�䣺${hour}ʱ��"/>
    <c:out value="${sayHello}"/>    <p />
    </h4>
</body>
</html>