<%request.setCharacterEncoding("gb2312");
response.setCharacterEncoding("gb2312");
%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="java.io.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Itzcn���</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
  <!-- ============= header start ============= -->
  <div id="header">
    <div id="banner">
      <h1 class="logo">Itzcn���</h1>
    </div>
    <!-- ============= �˵���(��ʼ) ============= -->
    <div id="menubar">
      <ul>
        <li class="active_menu"><a href="#">��ҳ</a></li>
        <li><a href="#">�������</a></li>
        <li><a href="#">�����б�</a></li>
        <li><a href="#">ͼ��ר��</a></li>
        <li><a href="#">�ҵ��˺�</a></li>
        <li><a href="#">�û�ע��</a></li>
        <li><a href="#">��ϵ����</a></li>
        <li><a href="#">�˲���Ƹ</a></li>
      </ul>
    </div>
    <!-- ============= �˵���(����) ============= -->
  </div>
  <!-- ============= header end ============= -->
  <div id="body" class="clear">
    <!-- ============= body_left start ============= -->
    <div id="body_left">
      <!-- ============= �����Ƽ�����ʼ�� ============= -->
      <h2><img src="imgs/bullet1.gif" />�ϴ����</h2>
      <div class="prod clear">
      <% 
   int MAX_SIZE = 102400 * 102400; //���������ļ�������ֽ� 
   String rootPath; // ������·���ı������ 
   DataInputStream in = null; //�����ļ������� 
   FileOutputStream fileOut = null; 
   String remoteAddr = request.getRemoteAddr(); //ȡ�ÿͻ��˵������ַ 
   //String serverName = request.getServerName(); //��÷����������� 
   //String realPath = request.getRealPath(serverName);//ȡ�û���������ľ��Ե�ַ 
   //realPath = realPath.substring(0,realPath.lastIndexOf("\\")); 
   rootPath = request.getSession().getServletContext().getRealPath("/")+ "upload/"; //�����ļ��ı���Ŀ¼
   out.println("<h3>�ϴ��ļ�����Ŀ¼Ϊ"+rootPath+"</h3>"); 
   String contentType = request.getContentType(); //ȡ�ÿͻ����ϴ����������� 
try{ 
   if(contentType.indexOf("multipart/form-data") >= 0){ 
   in = new java.io.DataInputStream(request.getInputStream()); //�����ϴ������� 
   int formDataLength = request.getContentLength(); 
   if(formDataLength > MAX_SIZE){ 
      out.println("<P>�ϴ����ļ��ֽ��������Գ���" + MAX_SIZE + "</p>"); 
       return; 
   } 
   byte dataBytes[] = new byte[formDataLength]; //�����ϴ��ļ������� 
   int byteRead = 0; 
   int totalBytesRead = 0; 
   while(totalBytesRead < formDataLength){ //�ϴ������ݱ�����byte���� 
         byteRead = in.read(dataBytes,totalBytesRead,formDataLength); 
        totalBytesRead += byteRead; 
} 
   String file = new String(dataBytes); //����byte���鴴���ַ��� 
   String saveFile = file.substring(file.indexOf("filename=\"") + 10); //ȡ���ϴ������ݵ��ļ��� 
   saveFile = saveFile.substring(0,saveFile.indexOf("\n")); 
   saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,saveFile.indexOf("\"")); 
   int lastIndex = contentType.lastIndexOf("="); 
   String boundary = contentType.substring(lastIndex + 1,contentType.length());//ȡ�����ݵķָ��ַ��� 
   String fileName = rootPath + saveFile; 
   int pos; 
   pos = file.indexOf("filename=\""); 
   pos = file.indexOf("\n",pos) + 1; 
   pos = file.indexOf("\n",pos) + 1; 
   pos = file.indexOf("\n",pos) + 1; 
   int boundaryLocation = file.indexOf(boundary,pos) - 4; 
   int startPos = ((file.substring(0,pos)).getBytes()).length;//ȡ���ļ����ݵĿ�ʼ��λ��  
   int endPos = ((file.substring(0,boundaryLocation)).getBytes()).length; //ȡ���ļ����ݵĽ�����λ�� 
   File checkFile = new File(fileName); //��������ļ��Ƿ���� 
   if(checkFile.exists()){ 
         out.println("<p>" + saveFile + "�ļ��Ѿ�����.</p>"); 
    } 
   File fileDir = new File(rootPath);//��������ļ���Ŀ¼�Ƿ����  
   if(!fileDir.exists()){ 
      fileDir.mkdirs(); 
    } 
   fileOut = new FileOutputStream(fileName); //�����ļ���д���� 
   fileOut.write(dataBytes,startPos,(endPos - startPos)); //�����ļ������� 
   fileOut.close(); 
    out.println("<p align=��center��><font color=red size=5>" + saveFile + "�ļ��ɹ��ϴ�.</font></p>"); 
   }
   else{ 
   String content = request.getContentType(); 
   out.println("<p>�ϴ����������Ͳ���multipart/form-data</p>"); 
    } 
   }catch(Exception ex)
    { 
         throw new ServletException(ex.getMessage()); 
   } 
%> 
<a href="uploadfile.jsp">�����ϴ��ļ�</a>
      
      </div>
      <!-- ============= �����Ƽ��������� ============= -->

    </div>
    <!-- ============= body_left end ============= -->
    <div id="body_right">
      <div id="cart" class="clear">
        <h2><img src="imgs/cart.gif" width="21" height="18" />���ﳵ</h2>
        <div class="info">2����Ʒ | <span>�ܼƣ�123��</span><a href="#">�鿴���ﳵ</a></div>
      </div>
      <img src="imgs/border.gif" width="329" height="31" />
      <div id="about_our">
        <h2><img src="imgs/bullet3.gif" width="26" height="25" />�������</h2>
        <div class="details clear"> <img src="images/about.jpg" />
          <p>�������ǿƼ���˾��עIT������ѵ�г��ķ�չ�ͱ仯�����������߽������ƽ���ֽ����ѧ���̡���������www.itzcn.com��������������ʵ�����󣬷�����Flex��C#��Java��ASP.NET��

JSP��ϵ����Ƶ�̡̳�</p>
        </div>
      </div>
      <img src="imgs/border.gif" width="329" height="31" />
      <div id="promotions">
        <h2><img src="imgs/bullet4.gif" width="26" height="25" />ͼ�����</h2>
        <ul class="list">
          <li><a href="#">��ά����</a></li>
          <li><a href="#">.NET����</a></li>
          <li><a href="#">Java����</a></li>
          <li><a href="#">PHP����</a></li>
          <li><a href="#">��������</a></li>
          <li><a href="#">Webǰ��</a></li>
          <li><a href="#">���ݿ⼼��</a></li>
          <li><a href="#">����Ӧ��</a></li>
        </ul>
      </div>
      <div class="right_box">
        <h2><img src="imgs/bullet6.gif" width="26" height="25" />��������</h2>
        <ul class="list">
          <li><a href="#">������</a></li>
          <li><a href="#">������̳</a></li>
          <li><a href="#">Google����</a></li>
          <li><a href="#">�ٶ�����</a></li>
        </ul>
      </div>
    </div>
  </div>
  <!-- ============= footer start ============= -->
  <div id="footer">
    <div class="logo"> <img src="imgs/logo.png" /> <img src="imgs/itzcn.gif" /> </div>
    <div class="nav">
      <ul>
        <li><a href="#">��ҳ</a></li>
        <li><a href="#">��������</a></li>
        <li><a href="#">����</a></li>
        <li><a href="#">����Э��</a></li>
        <li><a href="#">��ϵ����</a></li>
      </ul>
    </div>
    <img src="imgs/footer_bg.gif" /> </div>
  <!-- ============= footer end ============= -->
</div>
</body>
</html>