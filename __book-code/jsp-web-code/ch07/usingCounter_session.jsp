<%@page contentType="text/html"%>
<%@page pageEncoding="GB2312"%>
<html>	
    <head><title>ʾ��session���͵���������</title></head>
    <body>
    <jsp:useBean id="count"scope="session" class="bean.Counter"/>
	<font color="red">��Χ��session </font><br>	<br>	
	���Ǳ�վ��
        <font color=blue>
            <jsp:getProperty name="count" property="counter"/>
	</font>λ�ι���
    </body>
</html>   
