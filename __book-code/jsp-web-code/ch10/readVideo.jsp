<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.util.*"%> 
<%@ page import="java.text.*"%> 
<%@ page import="java.io.*"%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�����ݿ��ж�ȡ��Ƶ�ļ�</title>
<body  topmargin="0" leftmargin="0"> 
<%Class.forName("com.mysql.jdbc.Driver");
//����JDBC��������   
String  url="jdbc:mysql://localhost/bin_db"; 
//bin_dbΪ������ݿ������   
String  user="root";    
String  password="root";    
Connection  conn=  DriverManager.getConnection(url,user,password);
//�������ݿ����� 
String sql="select binfile from bindata where filename='05'"; 
Statement stmt=null; 
ResultSet rs=null; 
try{ 
    stmt=conn.createStatement(); 
    rs=stmt.executeQuery(sql); 
    }catch(SQLException e){} 
try { 
    String rootPath = application.getRealPath("/");//��ȡ��ǰӦ�ó���ĸ�Ŀ¼
  while(rs.next()) { 
        File f=new File(rootPath+"05");//��Ӧ�ó����Ŀ¼�������ȡ����Ƶ�ļ�
        FileOutputStream outs=new FileOutputStream(f);
        InputStream in = rs.getBinaryStream(1); //��ȡ������������
        byte b[] = new byte[0x7a120];// ����byte������������
        while (in.read(b) != -1) 
            { 
                outs.write(b); //����������ļ�
              
            } 
           outs.flush(); 
            outs.close(); 
            } 
            } 
      catch(Exception e){System.out.println(e);} 
%> 
<p>&nbsp;  </p>
<p align="center" class="style1"> �������Ű�ť�����ļ�</p>
<p>&nbsp;</p>
<p align="center">

<!--��ҳ���в���Windows Media Player���������������ļ�-->
<object  classid=clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95 codebase=
http://activex.microsoft.......ols/mplayer/en/nsmp2inf.cab#
Version=9.0 height=300 id=NSPlay0 name=NSPlay type=application/x-oleobject
 width=300 VIEWASTEXT standby="Loading Microsoft Windows Media Player
 components..." border="0">

   
    <!--���ò�����Ϊ�����Զ����š�-->
    <param name="AutoStart" value="0">
����������<!--���ò������Ĳ��Ų���-->
����������

       <!-- ���ò����ļ�������-->
        <param name="Filename" value="05">//���ò��ŵ���Ƶ�ļ�
������.<!--���ò������Ĳ��Ų���-->
������..
  </object>
</p>
</body> 
</html>
