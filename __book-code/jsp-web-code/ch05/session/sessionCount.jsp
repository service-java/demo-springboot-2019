<%@ page contentType="text/html;charset=GB2312"%>
<%!int Num = 0;%>
<%
	if (session.isNew()) {
		Num += 1;
		session.setAttribute("Num", Num);//��Num����ֵ����session
	} 
%>
<HTML>
<HEAD>
<TITLE>session������</TITLE>
</HEAD>
<BODY>
<CENTER><FONT SIZE=5>session������</FONT></CENTER>
<BR>
<CENTER><Font color=blue>���ǵ�</Font> 
<Font color=red><%=session.getAttribute("Num")%></Font>
<Font color=blue>�����ʱ�վ���û�</Font></CENTER>
</BODY>
</HTML>
