<%@ page contentType="text/html;charset=gb2312"%>
<jsp:useBean id="user" scope="page" class="ch04.TestBean" />
<jsp:setProperty name="user" property="userName" value="xingming" />
<jsp:setProperty name="user" property="*" />
<html>
<body>
ע��ɹ���
<br>
<hr>
ʹ��bean���Է�����
<br>
�û�����
<%=user.getUserName()%>
<br>
���룺
<%=user.getPassword()%>
<br>
���䣺
<%=user.getAge()%>
<br>
<hr>
ʹ��getProperty:
<br>
�û�����
<jsp:getProperty name="user" property="userName" />
<br>
���룺
<jsp:getProperty name="user" property="password" />
<br>
���䣺
<jsp:getProperty name="user" property="age" />
<br>
</body>
</html>
