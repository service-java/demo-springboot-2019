<%@page contentType="text/html"%>
<%@page pageEncoding="GB2312"%>
<html>	
    <head><title>ʾ��page���͵���������</title></head>
    <body>
    <jsp:useBean id="count" scope="page" class="bean.Counter"/>
	<font color="red">��ʾ��page </font><br>	<br>	
	���Ǳ�վ��
        <font color=blue>
            <jsp:getProperty name="count" property="counter"/>
	</font>λ�ι���
    </body>
</html>         
