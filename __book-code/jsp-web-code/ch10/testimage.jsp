<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.util.*"%> 
<%@ page import="java.text.*"%> 
<%@ page import="java.io.*"%>
<%@ page import="java.nio.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
    <body>
<%request.setCharacterEncoding("gb2312");%>
     <%
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        //����JDBC��������   
        String  url="jdbc:mysql://localhost/bin_db"; 
       //bin_dbΪ������ݿ������   
        String  user="root";    
        String  password="root";          
      
        String filename=request.getParameter("image");
         File file = new File(filename); //��ȡ����������ͼƬ��url           
        try {
			//���ļ�
			FileInputStream fin = new FileInputStream(file);
			//��һ�����屣������
			ByteBuffer nbf = ByteBuffer.allocate((int) file.length());
			byte[] array = new byte[1024];
			int offset = 0, length = 0;
			//��������
			while ((length = fin.read(array)) > 0) {
				if (length != 1024)
					nbf.put(array, 0, length);
				else
					nbf.put(array);
				offset += length;
			}
		  //�½�һ�����鱣��Ҫд������
			byte[] content = nbf.array();
			//�������ݿ�����
			Connection  conn=  DriverManager.getConnection(url,user,password);
			//��������
			Statement stmt =conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String sqlstr = "select * from bindata where filename='01'";
			ResultSet rs = stmt.executeQuery(sqlstr);
			if (rs.next()) 
			{
				rs.updateBytes(2, content);
				rs.updateRow();
			} else {
				rs.moveToInsertRow();
				rs.updateString(1, "01");
				rs.updateBytes(2, content);
				rs.insertRow();
			}
			rs.close();
//			�ر��ļ�
			fin.close();
			out.println("��ϲ���Ѿ����µļ�¼�ɹ�����ӵ����ݿ��У�");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
   %> 
    </body>
</html>
