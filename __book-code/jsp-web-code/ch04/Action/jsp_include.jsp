<%@ page contentType="text/html; charset=gb2312" language="java"%>
<html>
<body>
<%@ include file="static.html"%>
<%
//��̬����ֻ�ǰ��ļ���������
%>
<a href="action.jsp">goto two--></a>
<br>
<!-- ��������ת��action.jsp�ļ��Ͷ�̬�������ļ���ʾ�ǲ�ͬ�� -->
this examples show include works
<br>
<!-- ��̬�����ļ������ݲ��� -->
<jsp:include page="action.jsp" flush="true">
	<jsp:param name="a1" value="<%=request.getParameter("name")%>" />
	<jsp:param name="a2" value="<%=request.getParameter("password")%>" />
</jsp:include>
</body>
</html>
