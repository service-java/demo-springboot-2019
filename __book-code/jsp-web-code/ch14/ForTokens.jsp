<%@ page contentType="text/html;charset=GB2312" import="java.util.Vector" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html> <head><title>&lt;c:forTokens&gt;</title> </head>
  <body>
  <c:set var="strs" value="��|��,��|��,��|��,��" scope="request" />
	<h4><c:out value="ԭʼ����Ϊ��${strs}"/></h4>
	<c:out value="ʹ�� '|' ��Ϊ�ָ��ַ�:"/>
	<c:forTokens var="str" items="${strs}"  delims="|" varStatus="status">
  		<c:out value="${str}"/>  
  		<c:if test="${status.last}">
	    	<br/>�ܹ����<c:out value="${status.count}"/> ��Ԫ�ء�
	    </c:if>   
	</c:forTokens>
	<p />
	<c:out value="ͬʱʹ�� '|' �� ',' ��Ϊ�ָ��ַ�:"/>
	<c:forTokens var="str" items="${strs}"  delims="|," varStatus="status">
  		<c:out value="${str}"/>  
  		<c:if test="${status.last}">
	    	<br/>�����<c:out value="${status.count}"/> ��Ԫ�ء�
	    </c:if>   
	</c:forTokens>
   </body>
</html> 
