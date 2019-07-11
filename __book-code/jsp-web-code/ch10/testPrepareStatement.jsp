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
try {
	Class.forName("com.mysql.jdbc.Driver");
} catch(ClassNotFoundException e) {
	out.println("������������ʱ�����쳣");
}

try {
	conn = DriverManager.getConnection(url, userName, password);
	
	//����PreparedStatement���
	PreparedStatement pstmtDelete = conn.prepareStatement(
			"DELETE FROM student WHERE stu_id=?");		
	PreparedStatement pstmtInsert = conn.prepareStatement(
			"INSERT INTO student(name,address,birthday) VALUES(?, ?, ?)");	
	PreparedStatement pstmtSelect = conn.prepareStatement(
			"SELECT * FROM student WHERE stu_id>=? " +
			"ORDER BY stu_id");
	
	pstmtSelect.setInt(1, 1);
		
//	���ִ��ͬһ���
	for (int i=1; i<4; i++) {
		
        //ʹ��setXXX��������IN����
		pstmtDelete.setInt(1, i);
        
		pstmtInsert.setString(1, "name"+i);
		pstmtInsert.setString(2, "city"+i);
		pstmtInsert.setDate(3, new Date(85, 12, i));
		
        //ִ��PreparedStatement���
		pstmtDelete.executeUpdate();
		pstmtInsert.executeUpdate();
        ResultSet rs = pstmtSelect.executeQuery();
		
	    out.println("�� " + (i) + " ��ѭ����Ľ����Ϊ��"+"<br>");
        
//	  ��ʾ���صĽ����
		while (rs.next()) {
			int stuID      = rs.getInt(1);
			String name    = rs.getString(2);
			String address = rs.getString(3);
			String birthday= rs.getString(4);
			out.println(stuID + "   " + 
					name + "   " + address + "   " + birthday+"<br>");
		}
	}
	
	pstmtDelete.close();
	pstmtInsert.close();
	pstmtSelect.close();
	
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