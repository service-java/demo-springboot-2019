<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.jspsmart.upload.*"%> 
<jsp:useBean id="theSmartUpload" scope="page" class="com.jspsmart.upload.SmartUpload" /> 
<html> 
<head>
<title>���ظ��� </title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
  <center>�����ϴ��ļ�...

<%

//���ظ���

try
{

	theSmartUpload.initialize(pageContext); 
theSmartUpload.setTotalMaxFileSize(5*1024*1024) ;  
theSmartUpload.upload(); 
String fn=theSmartUpload.getFiles().getFile(0).getFileName();
theSmartUpload.save("C:\\saveDir\\");//�ļ������Ŀ¼Ϊupload
out.println(fn);
out.println("�Ѿ��ɹ��ϴ����ļ�����鿴�ļ��Ƿ��ϴ��ɹ�");
}
catch(Exception e)
{
e.printStackTrace();
}

%>
<a href=FileUpload.html>�����ϴ�</a>
</body>
</html>
