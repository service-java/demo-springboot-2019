<%@ page contentType="text/html; charset=gb2312"  language="java" errorPage=""%>
<%@ page import="java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>test executeUpdate</title>
</head>
<body>
<%String url = "jdbc:mysql://localhost/ch10";
String userName = "root";
String password = "root";
String sql = null;
Connection conn = null;
Statement stmt = null;
try {
	Class.forName("com.mysql.jdbc.Driver");
} catch(ClassNotFoundException e) {
	out.println("������������ʱ�����쳣");
}

try {
	conn = DriverManager.getConnection(url, userName, password);
	
	//����Statement���
	stmt = conn.createStatement();			
	sql = "DELETE FROM student WHERE stu_id=10";
	
	//ʹ��executeUpdateִ�и������
	int affectedRowCount = stmt.executeUpdate(sql);
	out.println("ɾ������Ӱ�����������Ϊ��" + affectedRowCount+"<br>");
	
	//ʹ��executeUpdateִ�и������
	sql = "INSERT INTO student(name,address,birthday)" +
	      "VALUES('С��', '����', '1980-05-10')";
	affectedRowCount = stmt.executeUpdate(sql);
	out.println("�������Ӱ�����������Ϊ��" + affectedRowCount+"<br>");
	
	sql = "update student set address='shanghai' where stu_id=11 " ;
    affectedRowCount = stmt.executeUpdate(sql);
    out.println("�޸Ĳ���Ӱ�����������Ϊ��" + affectedRowCount+"<br>");
	stmt.close();
	
} catch(SQLException e) {
	out.println("����SQLException�쳣");
} finally {
	//�ر��������ݿ�����
	try {
		if (conn != null) conn.close();
	} catch(SQLException e) {
		out.println("�ر����ݿ�����ʱ�����쳣");
	}		
}
 %>
</body>
</html>