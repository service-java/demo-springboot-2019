<%@ page contentType="text/html; charset=gb2312" language="java" 
import="java.sql.*,javax.naming.*,javax.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
��
<body>
<%
	out.print("ʹ�����ӳ�����MySQL���ݿ�ɹ���<br>");//�����ʾ��Ϣ
	out.println("<br>");
	Context ctx=null;
	DataSource ds=null;
	Statement stmt=null;
	ResultSet rs=null;
	Connection con=null;
	ResultSetMetaData md=null;
	try{
		ctx = new InitialContext();
		
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/my_db");//�ҵ����õ�����Դ
		con=ds.getConnection();//�������ݿ�����
		stmt=con.createStatement();	
		rs=stmt.executeQuery("select * from student");//ִ��sql��ѯ�����ݱ���ȡ������
		md=rs.getMetaData();//��ȡ���ݼ������������ֶ���
		out.print(md.getColumnLabel(1)+" ");//�����һ�е����ƣ�����һ���ֶ�����
		out.print(md.getColumnLabel(2)+" ");
		out.print(md.getColumnLabel(3)+" ");
		out.print(md.getColumnLabel(4)+"<br>");
		while(rs.next()){
			out.print(rs.getInt(1)+" ");//�����һ���ֶζ�Ӧ��ֵ
			out.print(rs.getString(2)+" ");
			out.print(rs.getString(3)+" ");
			out.print(rs.getString(4)+"<br>");
		}
	}catch(Exception e){
		out.print(e);
	}finally{
		if(rs!=null) rs.close();
		if(stmt!=null) stmt.close();
		if(con!=null) con.close();//�Ͽ����ݿ�����
                if(ctx!=null) ctx.close();//û������ʱ���ͷ���Դ

	}
%>
</body>
</html>
