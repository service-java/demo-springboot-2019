<%@ page contentType="text/html;charset=GB2312" import="java.util.Vector" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>&lt;c:forEach&gt;</title>
  </head>
  <body>
    <h3>&lt;c:forEach&gt;</h3>
    <h4>ѭ��10��</h4>
    <c:forEach var="item" begin="1" end="10">
        ${item}
    </c:forEach>
    <br/>step=3:
    <c:forEach var="item" begin="1" end="10" step="3">
        ${item}
    </c:forEach>
    <h4>ö��VectorԪ��</h4>
    <% 	Vector v = new Vector();
		v.add("����"); 
		v.add("�Ϻ�"); 
		v.add("����"); 
		v.add("֣��");
		pageContext.setAttribute("vector", v);
    %>
    <c:forEach items="${vector}" var="item" >
		${item}
    </c:forEach>
    <h4> ���ŷָ����ַ���</h4>
	<c:forEach var="color" items="��,��,��,�̣��࣬������" begin="2" step="3">
  		<c:out value="${color}"/>
	</c:forEach>
	<h4>״̬������ʹ��</h4>
	<c:forEach var="i" begin="5" end="50" step="5" varStatus="status">
	  <c:if test="${status.first}">
	    begin:<c:out value="${status.begin}"/>&nbsp; &nbsp; 
	      end:<c:out value="${status.end}"/>&nbsp; &nbsp; 
	     step:<c:out value="${status.step}"/><br>
	     <c:out value="�����Ԫ��:"/> 
	  </c:if>
	  <c:out value="${i}"/> 
	  <c:if test="${status.last}">
	    <br/>�ܹ����<c:out value="${status.count}"/> ��Ԫ�ء�
	  </c:if>  
	</c:forEach>
   </body>
</html> 
