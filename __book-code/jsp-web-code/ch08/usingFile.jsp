<%@page contentType="text/html"%>
  <%@page pageEncoding="GB2312"%>
  <%@page import="java.io.*"%>
  <html>
      <head><title>����File����</title></head>
      <body>
      <%
          String thePath=request.getRealPath("/")+"ch08\\theFile" ; 
          File myDir = new File(thePath)   ;
          File myFile = new File(thePath,"testFile.txt" )   ;
          File mynotExistFileFile = new
   			 File(thePath,"notExistFile.txt" )   ;
          out.println(
                  "Ŀ¼ "+thePath+ " �Ƿ���ڣ�" + 
                  myDir.exists() + "<BR>"  );
          out.println(
                  "�ļ� "+thePath+ "\\testFile.txt �Ƿ���ڣ�" + 
                  myFile.exists() + "<BR>" );
          out.println("�ļ� "+thePath+ "\\notExistFile.txt �Ƿ���ڣ�" +
          mynotExistFileFile.exists() + "<BR>"+ "<BR>" );
          out.println("�ļ� "+thePath+ "\\testFile.txt �Ƿ�ɶ�ȡ��" + 
          myFile.canRead() + "<BR>" );
          out.println("�ļ� "+thePath+ "\\testFile.txt �Ƿ��д�룺" + 
          myFile.canWrite() + "<BR>" );
      %>
      </body>
  </html>
