<%@page contentType="text/html"%>
<%@page pageEncoding="GB2312"%>
<html>
    <head><title>request ���͵���������</title></head>
    <body> 
    <jsp:useBean id="count"scope="request" class="bean.Counter"/>   
    	<font color="red">request.jsp</font><br><br>
	����request.jsp��ҳ�ĵ�<font color=blue>
	<jsp:getProperty name="count" property="counter"/>
	</font>λ�ι���<br>	
	<%   
            out.println(" ����request.jsp��ҳ�ļ����� !!");
	%>
    </body>
</html>
