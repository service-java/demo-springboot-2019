<%@page contentType="text/html"%>
<%@page pageEncoding="GB2312"%>
<html>	
    <head><title>request ���͵���������</title></head>
    <body>
    <jsp:useBean id="count"scope="request" class="bean.Counter"/>
    <font color="red">��Χ��request</font><br>
        ���Ǳ�վ��<font color=blue>
            <jsp:getProperty name="count" property="counter"/>
        </font>λ�ι���        
        <jsp:forward page="request.jsp" />
    </body>
</html>
