<%@page contentType="text/html"%>
<%@page pageEncoding="GB2312"%>
<html>
<head>
<title>EL ��������</title>
</head>
<body>
<%
	application.setAttribute("firstNum", "20");
	application.setAttribute("secondNum", "40");
%>

�����ֵ10��${10}
<br>
�������firstNum��${firstNum}
<br>
�������firstNum��${secondNum}
<br>
�������firstNum+firstNum��${secondNum+firstNum}
<br>
����ַ���firstNum��${'firstNum'}
<br>
����ַ���firstNum��${'secondNum'}
<br>
</body>
</html>
