<%@ page language="java" import="java.util.*" pageEncoding="GBK" info="ʹ��page����"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>page����</title>
<style type="text/css">
body {
	background-image: url(images/ypz.jpg);
	background-position: center top;
	background-repeat: no-repeat;
	text-align:center;
}
body {
	text-apgn: center;
	padding-top: 70px;
	color: #805231;
	font-family: "Monotype Corsiva ����";
}

p {
	font-size: 14px;
}

h1 {
	font-size: 30px;
	padding-bottom: 20px;
}
</style>
</head>
<body>
	<%
		String info;
		info = ((javax.servlet.jsp.HttpJspPage) page).getServletInfo();
		out.print("<h1>" + info + "<h1>");
	%>
	<%! Object obj;//����һ��Object����%>
	<p>getClass()�����ķ���ֵ��<%=page.getClass()%></p>	
	<p>toString()�����ķ���ֵ<%=page.toString()%></p>
	<p>hashCode()�����ķ���ֵ��<%=page.hashCode()%></p>
	<p>��Object����Ƚϵķ���ֵ��<%=page.equals(obj)%></p>
	<p>��this����Ƚϵķ���ֵ��<%=page.equals(this)%></p>
</body>
</html>
