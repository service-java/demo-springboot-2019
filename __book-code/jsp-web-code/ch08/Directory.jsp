<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.io.*"%>
<html>
<head>
<title>Ŀ¼�Ĵ����������ɾ��</title>
</head>
<body>
<%
String path=request.getRealPath("");
path=path + "\\text";
//��Ҫ������Ŀ¼·��
File d=new File(path);
//��������uploadFileĿ¼��File���󣬲��õ�����һ������

if(d.exists())
{
//���uploadFileĿ¼�Ƿ����

d.delete();
out.println("uploadFileĿ¼���ڣ��ֱ�ɾ��");
}else
{
d.mkdir();
//����uploadFileĿ¼

out.println("uploadFileĿ¼�����ڣ��ֱ�����");
}
%>
</body>
</html>
