<%@ page contentType="text/html; charset=gb2312"  language="java" errorPage=""%>
<%@ page import="java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>test execute</title>
</head>
<body>
<%String sql=request.getParameter("sqltest"); 
if(sql==null){%>
<form name="Example" method="post" action="">
<p> sql��䣺<input type="text" name="sqltest" size="100" maxlength="100"></p>
<p><input type="submit" value="����"></p>
<%}else{ 
String url = "jdbc:mysql://localhost/ch10";
String userName = "root";
String password = "root";
Connection conn = null;
Statement stmt = null;

try {
	Class.forName("com.mysql.jdbc.Driver");
} catch(ClassNotFoundException e) {
	out.println("������������ʱ�����쳣"+"<br>");
}

try {
	conn = DriverManager.getConnection(url, userName, password);
	
	//����Statement���
	stmt = conn.createStatement();	

	out.println("ִ�е�SQL���Ϊ��" + sql+"<br>");
	
	//ʹ��executeִ��δ֪SQL���
	boolean isResultSet = stmt.execute(sql);
	int count = 0;

	while (true) {
		count ++;
		if (isResultSet) {
			ResultSet rs = stmt.getResultSet();
			out.println("���ص�ִ�н�� " + count + " Ϊ�����"+"<br>");
			//��ʾ���صĽ����
			while (rs.next()) {
				int f1 = rs.getInt(1);
				String f2 = rs.getString(2);
				String f3 = rs.getString(3);
				out.println(f1 + "   " + f2 + "   " + f3);
				out.println("<br>");
			}
			rs.close();
		}
		else {
			int affectedRowCount = stmt.getUpdateCount();
			if (affectedRowCount == -1) break;
			out.println("���ص�ִ�н�� " + count + " Ϊ���¼���"+"<br>");
			out.println("���¼���Ϊ��" + affectedRowCount+"<br>");
		}
		
		isResultSet = stmt.getMoreResults();
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
} }%>
</form>
</body>
</html>