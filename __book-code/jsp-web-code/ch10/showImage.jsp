<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.util.*"%> 
<%@ page import="java.text.*"%> 
<%@ page import="java.io.*"%>
<html> 
<body> 
<%Class.forName("com.mysql.jdbc.Driver");
//����JDBC��������   
String  url="jdbc:mysql://localhost/bin_db"; 
//bin_dbΪ������ݿ������   
String  user="root";    
String  password="root";    
Connection  conn=  DriverManager.getConnection(url,user,password);
//�������ݿ�����
String sql="select binfile from bindata where filename='01'"; //��ѯfilenameΪ01�ļ�¼��pic�ֶ�
Statement stmt=null; 
ResultSet rs=null; 
try{ 
    stmt=conn.createStatement(); 
    rs=stmt.executeQuery(sql); 
    }catch(SQLException e){} 
try { 
    while(rs.next()) { 
      response.setContentType("image/jpeg");  //���÷���ͼ�������
        ServletOutputStream sout = response.getOutputStream();
//����ServletOutputStream��ʵ��sout
        InputStream in = rs.getBinaryStream(1); //��ȡ������������
        byte b[] = new byte[0x7a120];// ����byte������������
        for(int i = in.read(b); i != -1;) 
            { 
                sout.write(b); //���������д�뷵��ҳ������� 
                in.read(b); //��ȡ�������е����� 
            } 
            sout.flush(); 
            sout.close(); //�ر�������
            } 
            } 
      catch(Exception e){System.out.println(e);} 
%> 
</body> 
</html>
