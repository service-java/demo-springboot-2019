<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>��ʫ</title>
<style type="text/css">
body {
	background-image: url(images/2005617192843333.jpg);
	background-repeat: no-repeat;
}

.main {
	margin-top: 130px;
	margin-left: 20px;
	font-size: 12px;
}

h2 {
	font-size: 22px;
}
</style>
</head>
<body>
	<div class="main">
		<%
			String[] strs = { "���ú������ɫ����¥�뿪��б�ס�", "������¶ʪ�Ź⣬���������İ��",
					"�Ƴ���ˮ��ɽ�£�����ǧ��������", "ң�����ݾŵ��̣�һ����ˮ����к��" };
			out.println("<h2>����</h2>");
			for (int i = 0; i < strs.length; i++) {
				out.print(strs[i]);
				out.print("<br/><br/>");
			}
		%>

	</div>
</body>
</html>
