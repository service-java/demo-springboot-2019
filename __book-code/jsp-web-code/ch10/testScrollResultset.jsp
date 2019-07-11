<%@ page contentType="text/html; charset=gb2312"  language="java" errorPage=""%>
<%@ page import="java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>test ScrollResultset</title>
</head>
<body>
<%String url = "jdbc:mysql://localhost/ch10";
String userName = "root";
String password = "root";
Connection conn = null;
try {
	Class.forName("com.mysql.jdbc.Driver");
} catch(ClassNotFoundException e) {
	out.println("������������ʱ�����쳣");
}

try {
	conn = DriverManager.getConnection(url, userName, password);
	
	//�������ؿɹ����������������
	Statement stmt = conn.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE, 
			ResultSet.CONCUR_READ_ONLY);
		
	//ִ��SQL��ѯ���õ��ɹ��������
	ResultSet rs = stmt.executeQuery("SELECT * FROM student");
	
	out.println("��ǰ�α��Ƿ��ڵ�һ��֮ǰ��" + rs.isBeforeFirst()+"<br>");
	
	out.println("��ǰ����˳����ʾ�������"+"<br>");
	
	//ʹ��next()����˳����ʾ�����
	while (rs.next()) {	
		int id      = rs.getInt(1);
		String name    = rs.getString(2);
		String address = rs.getString("address");
		Date birthday = rs.getDate(4);
		
		out.println(id + "   " + name + "   " + 
				address + "   " + birthday+"<br>");
	}
	
	out.println("��ǰ�α��Ƿ������һ��֮��" + rs.isAfterLast()+"<br>");

	out.println("�ɺ���ǰ������ʾ�������"+"<br>");
	
	//ʹ��previous()����������ʾ�����
	while (rs.previous()) {	
		int id      = rs.getInt(1);
		String name    = rs.getString(2);
		String address = rs.getString("address");
		Date birthday = rs.getDate(4);
		
		out.println(id + "   " + name + "   " + 
				address + "   " + birthday+"<br>");
	}
	
	out.println("���α��ƶ�����һ��"+"<br>");
	rs.first();
	out.println("��ǰ�α��Ƿ��ڵ�һ�У�" + rs.isFirst()+"<br>");
	out.println("�������һ�е�����Ϊ��"+"<br>");
	out.println(rs.getInt(1) +  "   " + rs.getString(2) + 
			"   " + rs.getString(3) +  "   " + rs.getDate(4)+"<br>");
	
	out.println("���α��ƶ������һ��"+"<br>");
	rs.last();
    out.println("��ǰ�α��Ƿ������һ�У�" + rs.isLast()+"<br>");
	out.println("��������һ�е�����Ϊ��"+"<br>");
	out.println(rs.getInt(1) +  "   " + rs.getString(2) + 
		"   " + rs.getString(3) +  "   " + rs.getDate(4)+"<br>");

	//�α����Զ�λ
	out.println("���α��ƶ������һ�е�ǰ����"+"<br>");
	rs.relative(-2);
	out.println("��������һ�е�ǰ���е�����Ϊ��"+"<br>");
	out.println(rs.getInt(1) +  "   " + rs.getString(2) + 
			"   " + rs.getString(3) +  "   " + rs.getDate(4)+"<br>");
	
    //�α�ľ��Զ�λ
	out.println("���α��ƶ����ڶ���"+"<br>");
	rs.absolute(2);
	out.println("������ڶ��е�����Ϊ��"+"<br>");
	out.println(rs.getInt(1) +  "   " + rs.getString(2) + 
			"   " + rs.getString(3) +  "   " + rs.getDate(4)+"<br>");
	
    //beforeFirst()������next()���������ʹ��
    out.println("�Ƚ��α��ƶ�����һ��֮ǰ"+"<br>");
	rs.beforeFirst();
	out.println("�ٴ���ǰ����˳����ʾ�������"+"<br>");		
	while (rs.next()) {	
		int id      = rs.getInt(1);
		String name    = rs.getString(2);
		String address = rs.getString("address");
		Date birthday = rs.getDate(4);
		
		out.println(id + "   " + name + "   " + 
				address + "   " + birthday+"<br>");
	}
	
	rs.close();
	stmt.close();
	
} catch(SQLException e) {
	out.println("����SQLException�쳣"+"<br>");
} finally {
	//�ر��������ݿ�����
	try {
		if (conn != null) conn.close();
	} catch(SQLException e) {
		out.println("�ر����ݿ�����ʱ�����쳣"+"<br>");
	}		
}%>

</body>
</html>