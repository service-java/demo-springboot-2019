<%@ page contentType="text/html;charset=gb2312"%>��
<%@ page import="java.sql.*" %> 
<%@ page import="java.util.*"%> 
<%@ page import="java.text.*"%> 
<%@ page import="java.io.*"%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�����ݿ��ж�ȡ�����ļ�</title>
<style type="text/css">
<!--
body {
	background-color: #FFFFCC;
}
.style1 {
	color: #FF0000;
	font-size: 18px;
}
-->
</style></head>
<body  topmargin="0" leftmargin="0"> 
<%Class.forName("com.mysql.jdbc.Driver").newInstance();
//����JDBC��������   
String  url="jdbc:mysql://localhost/bin_db"; 
//bin_dbΪ������ݿ������   
String  user="root";    
String  password="root";    
Connection  conn=  DriverManager.getConnection(url,user,password);
//�������ݿ�����
String sql="select * from bindata where filename='03'"; 
Statement stmt=null; 
ResultSet rs=null; 
try{ 
    stmt=conn.createStatement(); 
    rs=stmt.executeQuery(sql); 
    }catch(SQLException e){} 
try { 
    String rootPath = application.getRealPath("/");//��ȡ��ǰӦ�ó���ĸ�Ŀ¼
   if (rs.next()) { 
        File f=new File(rootPath+"03");//��Ӧ�ó����Ŀ¼�������ȡ�������ļ�
        FileOutputStream outs=new FileOutputStream(f);
        InputStream in = rs.getBinaryStream(2); //��ȡ������������
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
<p align="center" class="style1"> �������Ű�ť���������ļ�</p>
<p>&nbsp;</p>
<p align="center">

<!--��ҳ���в���Windows Media Player�������������������ļ�-->
  <object  classid=clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95 codebase=
http://activex.microsoft.......ols/mplayer/en/nsmp2inf.cab#
Version=9.0 height=53 id=NSPlay0 name=NSPlay type=application/x-oleobject
 width=300 VIEWASTEXT standby="Loading Microsoft Windows Media Player
 components..." border="0">
    <param name="AudioStream" value="-1">
    <param name="AutoSize" value="0">
   
    <!--���ò�����Ϊ�����Զ����š�-->
    <param name="AutoStart" value="0">
����������<!--���ò������Ĳ��Ų���-->
����������

       <!-- ���ò����ļ�������-->
       <param name="Filename" value="03">
������.<!--���ò������Ĳ��Ų���-->
������..
  </object>

</p>
</body> 
</html>

