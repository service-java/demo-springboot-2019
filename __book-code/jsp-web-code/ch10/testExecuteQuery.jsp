<%@ page contentType="text/html; charset=gb2312"  language="java" errorPage=""%>
<%@ page import="java.sql.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>test executeQuery</title>
</head>
<body>
<%
String url = "jdbc:mysql://localhost/ch10";
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
			sql = "SELECT * FROM student";
			
			//ʹ��executeQueryִ��SQL��ѯ���
			ResultSet rs = stmt.executeQuery(sql);
			%>
	<table width="740" border="1" cellspacing="0" cellpadding="6">
    <tr> 
      <td width="120" align="center" valign="middle">���</td>
      <td width="145" align="center">����</td>
      <td width="253" align="center">��ַ</td>
      <td width="148" align="center">��������</td>
    </tr>
			<%
			//��ʾ���صĽ����
			while (rs.next()) {
				int id      = rs.getInt(1);
				String name    = rs.getString(2);
				String address = rs.getString(3);
				String birthday     = rs.getString(4);						
			%>
			<tr> 
      <td height="40" align="center" valign="middle"><%=id%></td>
      <td align="center" valign="middle"><%=name%></td>
      <td valign="middle"><%=address%></td>    
       <td align="center" valign="middle"><%=birthday%></td>  
    </tr>
			<%}
			rs.close();
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