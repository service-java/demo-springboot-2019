<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
	request.setCharacterEncoding("gb2312");
	response.setCharacterEncoding("gb2312"); //������Ӧ�����ʽ
	response.setContentType("text/html;charset=gb2312");
	String pathname = request.getParameter("path"); //��ȡ���ݹ����Ĳ���
	pathname = new String(pathname.getBytes("iso8859-1"), "gb2312");
	File file = new File(pathname); //����File����
	out.println(java.net.URLEncoder.encode(file.getName(), "iso8859-1"));
	out.println(java.net.URLEncoder.encode(file.getName(), "gb2312"));
	out.println(URLDecoder.decode(file.getName(), "utf-8"));
	if (file.exists() == false) {
		out.println("<p align ='center'>�ļ������ڻ��ѱ�ɾ��������ʧ��!!<p>");
	} else {
		InputStream ins = new FileInputStream(file); //����InputStream���󣬶�ȡ�ļ�
		OutputStream os = response.getOutputStream(); //��ȡ��Ӧ�����
		response.addHeader(
				"Content-Disposition",
				"attachment;filename="
						+ java.net.URLEncoder.encode(file.getName(),
								"gb2312"));
		response.addHeader("Content-Length", file.length() + "");
		response.setContentType("application/octet-stream"); //������Ӧ��������
		int data = 0;
		while ((data = ins.read()) != -1) { //���ļ�����ѭ����ȡ�ֽ�
			os.write(data); //������ֽ���
		}
		out.clear();
		out = pageContext.pushBody();
		os.close();
		ins.close();
	}
%>
