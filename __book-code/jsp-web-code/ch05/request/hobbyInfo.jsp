<%@ page contentType="text/html;charset=gb2312" language="java"%>
<%request.setCharacterEncoding("gb2312");%>
<html>
<head>
<title>��ʾ�û���Ϣ</title>
</head>
<body>
��Ȥ��
<%
	String[] hobby = request.getParameterValues("Habit");
	if (hobby != null) {
		for (int i = 0; i < hobby.length; i++) {
			if (hobby[i].equals("Read")) {
		out.println("���� ");
			}
			if (hobby[i].equals("Football")) {
		out.println("���� ");
			}
			if (hobby[i].equals("Travel")) {
		out.println("���� ");
			}
			if (hobby[i].equals("Music")) {
		out.println("������ ");
			}
			if (hobby[i].equals("Tv")) {
		out.println("������ ");
			}
		}
	}
%>
</body>
</html>

