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
	out.println("������������ʱ�����쳣"+"<br>");
}

try {
	conn = DriverManager.getConnection(url, userName, password);
	
	//�������ؿɸ��½������������
	Statement stmt = conn.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE, 
			ResultSet.CONCUR_UPDATABLE);
		
	//ִ��SQL��ѯ���õ��ɸ��½����
	ResultSet rs = stmt.executeQuery(
			"SELECT * FROM student ORDER BY stu_id");
				
    out.println("�޸�֮ǰ�Ľ������");
	while (rs.next()) {	
		int id      = rs.getInt(1);
		String name    = rs.getString(2);
		String address = rs.getString("address");
		Date birthday = rs.getDate(4);		
	   out.println(id + "   " + name + "   " + 
				address + "   " + birthday+"<br>");
	}
	
	//���α��ƶ������һ��
	rs.last();			
	int stuID = rs.getInt("stu_id");
	
	//ʹ��updateXXX����������ֵ			
	rs.updateString(2, "newName"+Integer.toString(stuID));
	rs.updateString("address", "newAddr");
	rs.updateDate("birthday", new Date(79, 7, stuID%29));
	
	//ʹ��updateRow()�����ύ���½��
	rs.updateRow();
	
	//���α��ƶ���������
	rs.moveToInsertRow();
	stuID++;
	
    //ʹ��updateXXX����������ֵ
	rs.updateInt("stu_id", stuID);
	rs.updateString(2, "Name"+Integer.toString(stuID));
	rs.updateString("address", "Addr"+Integer.toString(stuID));
	rs.updateDate("birthday", new Date(79, 7, stuID%29));
	
	//ʹ��insertRow()�����ύ������
	rs.insertRow();
	
    //���α��ƶ�����ǰ��
	rs.moveToCurrentRow();
	rs.previous();
	
	//ʹ��deleteRow()����ɾ��һ��
	rs.deleteRow();
	rs.close();
	
	//���²�ѯ����ʾ�����
	rs = stmt.executeQuery(
			"SELECT * FROM student ORDER BY stu_id");
	
	out.println("�޸�֮��Ľ������"+"<br>");
	while (rs.next()) {	
		int id      = rs.getInt(1);
		String name    = rs.getString(2);
		String address = rs.getString(3);
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