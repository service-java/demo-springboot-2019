<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.io.*,java.lang.*"%>
<html>
<head>
<title>��д�ı��ļ�</title>
</head>
<body>
<%
String path=request.getRealPath("");
//����FileWriter���󣬲�ʵ����fw
FileWriter fw=new FileWriter(path + "\\test.txt");
//���ַ���д���ļ�
fw.write("��Һã�");
fw.write("hello��");
fw.write("����ָ�̣�");
fw.close();
FileReader fr=new FileReader(path + "\\test.txt");
//�ڶ�ȡ�����У�Ҫ�ж�����ȡ���ַ��Ƿ��Ѿ������ļ���ĩβ����������ַ��ǲ����ļ��еĻ��з������жϸ��ַ�ֵ�Ƿ�Ϊ13��
int c=fr.read();//���ļ��ж�ȡһ���ַ�
//�ж��Ƿ��Ѷ����ļ���β
while(c!=-1)
{
//�������������
out.print((char)c);
//���ļ��м�����ȡ����
c=fr.read();
//�ж��Ƿ�Ϊ�����ַ�
if(c==13)
{
//������б�ǩ
out.print("<br>");
//�Թ�һ���ַ�
fr.skip(1);
//��ȡһ���ַ�
c=fr.read();
}
}
fr.close();
%>
</body>
</html>
