<%@ page contentType="text/html; charset=GBK" %> 
<%@ page import="java.io.*"%> 
<%@ page import="java.util.*"%> 
<%@ page import="javax.servlet.*"%> 
<%@ page import="javax.servlet.http.*"%> 
<html><head><title>upFile</title></head> 
<body bgcolor="#ffffff"> 
<% 
   int MAX_SIZE = 102400 * 102400; //���������ļ�������ֽ� 
   String rootPath; // ������·���ı������ 
   DataInputStream in = null; //�����ļ������� 
   FileOutputStream fileOut = null; 
   String remoteAddr = request.getRemoteAddr(); //ȡ�ÿͻ��˵������ַ 
   String serverName = request.getServerName(); //��÷����������� 
   String realPath = request.getRealPath("/");//ȡ�û���������ľ��Ե�ַ 
   realPath = realPath.substring(0,realPath.lastIndexOf("\\")); 
   rootPath = realPath + "\\upload\\"; //�����ļ��ı���Ŀ¼
   out.println("�ϴ��ļ�����Ŀ¼Ϊ"+rootPath); 
   String contentType = request.getContentType(); //ȡ�ÿͻ����ϴ����������� 
 try{ 
   if(contentType.indexOf("multipart/form-data") >= 0){ 
   in = new DataInputStream(request.getInputStream()); //�����ϴ������� 
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
    out.println("<P><font color=red size=5>" + saveFile + "�ļ��ɹ��ϴ�.</font></p>"); 
   }
   else{ 
   String content = request.getContentType(); 
   out.println("<p>�ϴ����������Ͳ�����multipart/form-data</p>"); 
    } 
   }catch(Exception ex)
    { 
         throw new ServletException(ex.getMessage()); 
   } 
%> 
<a href="uploadfile.jsp">�����ϴ��ļ�</a>
</body> 
</html> 
