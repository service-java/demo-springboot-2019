 <%@ page contentType="text/html;charset=gb2312"%>
 <%@ page import="java.io.*"%>

<html>
<head>
<title>�ļ������</title>
</head>
<body>
<h1>�ļ������</h1>
 
 <%int fcount=0,dcount=0;%>
 <%
  String path=request.getRealPath("/");
 
 //������ǰĿ¼���ļ���File����
 File d=new File(path);
 //ȡ�ô���Ŀ¼�������ļ���File��������
  File list[]=d.listFiles();
 out.println("<font color=#ff0000>" + path + "Ŀ¼�µ��ļ���</font><br>");
 //ѭ�������ǰĿ¼�µ������ļ�
 for(int i=0;i<list.length;i++)
  {
 if(list[i].isFile())
 {
  out.println(list[i].getName() + "<br>");
 fcount++;
  }
  }
  out.println("<br><font color=#ff0000>" + path + "Ŀ¼�µ�Ŀ¼��</font><br>");
  //ѭ�������ǰĿ¼�µ�������Ŀ¼
  for(int i=0;i<list.length;i++)
  {
  if(list[i].isDirectory())
  {
  out.println(list[i].getName() + "<br>");
  dcount++;
  }
  }
  %>
  
  <hr>
  <h3>ͳ�ƽ����</h3>
  <center>
  �ļ�������<%=fcount%><br>
  Ŀ¼������<%=dcount%><br>
  </center>
  </body>
  </html>
