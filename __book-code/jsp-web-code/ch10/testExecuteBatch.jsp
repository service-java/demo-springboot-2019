<%@ page contentType="text/html; charset=gb2312"  language="java" errorPage=""%>
<%@ page import="java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>test executeBatch</title>
</head>
<body>
<%String url = "jdbc:mysql://localhost/ch10";
String userName = "root";
String password = "root";
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
	
	//ʹ��addBatch�������һ��ɾ�����
	stmt.addBatch("DELETE FROM student WHERE stu_id=13");
    //ʹ��addBatch�������һ���������
	stmt.addBatch("INSERT INTO student " +
	              "VALUES(13, 'wang', 'beijing', '1980-05-01')");
    stmt.addBatch("USE ch10");
//  ʹ��addBatch�������һ��DROP TABLE DDL���
	stmt.addBatch("DROP TABLE if exists test_table");
    //ʹ��addBatch�������һ��CREATE TABLE DDL���
	stmt.addBatch("CREATE TABLE test_table " +
			      "(clumn1 CHAR(10),clumn2 CHAR(20))");
	
	//ʹ��executeBatchִ�������������
	int[] affectedRowCounts = stmt.executeBatch();
	
	//��ʾ���¼�������
	for (int i=0; i<affectedRowCounts.length; i++) {
		out.println("��" + (i+1) + "���������Ӱ�����������Ϊ��" + affectedRowCounts[i]+"<br>");
	}
	
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
}%>

</body>
</html>