<%@ page contentType="text/html;charset=gb2312"%>��
<%@ page import="java.sql.*" %> 
<%@ page import="java.util.*"%> 
<%@ page import="java.text.*"%> 
<%@ page import="java.io.*"%>
<html>
<body>
<%request.setCharacterEncoding("gb2312");%>
<%
Class.forName("com.mysql.jdbc.Driver");
//����JDBC��������   
      String  url="jdbc:mysql://localhost/bin_db"; 
//bin_dbΪ������ݿ������   
      String  user="root";    
      String  password="root";    
      Connection  conn=  DriverManager.getConnection(url,user,password);
//�������ݿ�����
         String filename=request.getParameter("sound");
        File file = new File(filename); //��ȡ����������������url           
        try {
			//���ļ�
			FileInputStream fin = new FileInputStream(file);
//��ȡ�����ļ���ת��Ϊ���ַ����ַ�������
       String sql1="delete from bindata where filename='03'";
       PreparedStatement pstmt1=conn.prepareStatement(sql1); //����PreparedStatement����
       pstmt1.execute();
        String sql="insert into bindata(filename,binfile) values('03',?)"; //�����¼��sql���
        PreparedStatement pstmt=conn.prepareStatement(sql); //����PreparedStatement����
        pstmt.setBinaryStream(1,fin,fin.available()); //���ַ�������in�洢��pstmt������
        pstmt.execute(); //pstmt����¼���뵽���ݿ���
        out.println("��ϲ�������ļ��Ѿ��ɹ��ش洢�����ݿ��У�"); }
        catch(SQLException e) {
        	out.println("����SQLException�쳣");
        }
   %> 
     </body>
</html>
