<%@ page contentType="text/html; charset=gb2312"  language="java" errorPage=""%>
<%@ page import="java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>test connection</title>
</head>
<body>
<% 
	String url = "jdbc:mysql://localhost/ch10";
		String userName = "root";
		String password = "root";
		Connection conn = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			} catch(ClassNotFoundException e) {
			
			out.println("������������ʱ�����쳣");
		}try {
			conn = null;
			conn = DriverManager.getConnection(url, userName, password);
			} catch(SQLException e) {
			out.println("�������ݿ�Ĺ����г���SQL�쳣");
		} if (conn==null)
			out.println("�������ݿ�ʧ��");
		else
			out.println("�������ݿ�ɹ�");	
		try {
			conn.close();
		} catch (SQLException e) {
			out.println("�ر����ݿ�����ʱ����SQL�쳣");
		} 
		%>
</body>
</html>
