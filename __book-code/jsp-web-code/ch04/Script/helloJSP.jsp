<%@ page contentType="text/html; charset=GBK"%>
<html>
<head>
<title>�ҵĵ�һ��JSP���򣡣���</title>
</head>
<body>
<%!int number = 1;%>
<%--��������һ������ --%>
<%!public int count() {
		return number++;
	}

	/*��������һ������*/%>
<%
	//JSP�������
	out.println("Hello JSP! ");
	out.println("��ӭʹ�� JSP����ʽ��̬��ҳ!!  ");
%>
<br>
<%="���ǵ�" + count() + "������!"%>
<br>
</body>
</html>
