<%@ page contentType="text/html; charset=gb2312"%>
<html>
<head>
<title>jsp:setProperty����ʵ��</title>
</head>
<body>
<h1><jsp:useBean id="clock" class="java.util.Date">
	<jsp:setProperty name="clock" property="hours" param="hh" />
	<jsp:setProperty name="clock" property="minutes" value="79" />
	<jsp:setProperty name="clock" property="seconds" param="*" />
</jsp:useBean> 
�������Ժ��ʱ�̣�<%=clock%> <br>
������<%=clock.getHours()%>�㣬 
<%=clock.getMinutes()%>�֣� 
<%=clock.getSeconds()%>��
</body>
</html>
