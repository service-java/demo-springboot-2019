<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>out����</title>
</head>
<body>
	<%
		out.println("<h2>����</h2>");
		out.clearBuffer();
		out.println("<h2>Hello out����!</h2>");
		out.flush();
		out.print("ʣ�໺������СΪ��" + out.getRemaining() + "bytes<BR>");
		out.print("Ĭ�ϻ�������СΪ��" + out.getBufferSize() + "bytes<BR>");
		out.print("ʣ�໺������СΪ��" + out.getRemaining() + "bytes<BR>");
		out.print("�Ƿ�ʹ��Ĭ��AutoFlush��" + out.isAutoFlush());
	%>
</body>
</html>
