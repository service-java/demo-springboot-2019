<%@ page contentType="text/html;charset=GB2312"%>
<%@ page import="java.util.*"%>
<HTML>
<head>
<title>reuqest����ʾ��</title>
</head>
<BODY>
<BR>
�ͻ�ʹ�õ�Э����:
<%=request.getProtocol()%>
<BR>
��ȡ���ܿͻ��ύ��Ϣ��ҳ�棺
<%=request.getServletPath()%>
<BR>
���ܿͻ��ύ��Ϣ�ĳ��ȣ�
<%=request.getContentLength()%>
<BR>
�ͻ��ύ��Ϣ�ķ�ʽ��
<%=request.getMethod()%>
<BR>
��ȡHTTPͷ�ļ���User-Agent��ֵ��
<%=request.getHeader("User-Agent")%>
<BR>
��ȡHTTPͷ�ļ���Host��ֵ��
<%=request.getHeader("Host")%>
<BR>
��ȡHTTPͷ�ļ���accept��ֵ��
<%=request.getHeader("accept")%>
<BR>
��ȡHTTPͷ�ļ���accept-encoding��ֵ��
<%=request.getHeader("accept-encoding")%>
<BR>
��ȡ�ͻ��������ƣ�
<%=request.getRemoteHost()%>
<BR>
��ȡ�ͻ���IP��ַ��
<%=request.getRemoteAddr()%>
<BR>
��ȡ�����������ƣ�
<%=request.getServerName()%>
<BR>
��ȡ�������Ķ˿ںţ�
<%=request.getServerPort()%>
<BR>
ö�����е�ͷ�����ƣ�
<%
	Enumeration enum_headed = request.getHeaderNames();
	while (enum_headed.hasMoreElements()) {
		String s = (String) enum_headed.nextElement();
		out.println(s);
	}
%>

<BR>
ö��ͷ����Ϣ��ָ��ͷ���ֵ�ȫ��ֵ��
<%
	Enumeration enum_headedValues = request.getHeaders("cookie");
	while (enum_headedValues.hasMoreElements()) {
		String s = (String) enum_headedValues.nextElement();
		out.println(s);
	}
%>
</BODY>
</HTML>
