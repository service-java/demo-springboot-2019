<%@ page contentType="text/html;charset=GB2312"%>
<%
	int Num = 0;
	String strNum = (String) application.getAttribute("Num");

	//����Ƿ�Num�����Ƿ��ȡ��,�����ȡ�ý���ֵ��1
	if (strNum != null)
		Num = Integer.parseInt(strNum) + 1;

	application.setAttribute("Num", String.valueOf(Num)); //��Num����ֵ����application
%>
<HTML>
<HEAD>
<TITLE>application����ʾ��</TITLE>
</HEAD>
<BODY>
<CENTER><FONT SIZE=5>application����ʾ��</FONT></CENTER>
<HR>
��ҳ���Ӧ��ʵ��·����:
<BR>
<%=application.getRealPath("application.jsp")%>
<BR>
<BR>
<Font color=blue>���Ѿ�����ҳ��</Font>
<Font color=red><%=Num%></Font>
<Font color=blue>��</Font>
</BODY>
</HTML>
