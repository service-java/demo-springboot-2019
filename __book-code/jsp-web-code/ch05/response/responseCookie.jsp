<%@page contentType="text/html;charset=GB2312"%>
<%@ page import="javax.servlet.http.Cookie,java.util.*"%>
<TITLE>responseӦ��ʵ��</TITLE>
<%
	//ͨ��request����Cookie�е����ݶ���
	Cookie[] cookies = request.getCookies();
	Cookie cookie_response = null;
	if (cookies == null) // ���û���κ�Cookie
		out.print("û��Cookie" + "<br>");
	else {
		try {
			if (cookies.length == 0) {
		System.out.println("�ͻ��˽�ֹд��cookie");
			} else {

		for (int i = 0; i < cookies.length; i++) { // ѭ���г����п��õ�Cookie

			Cookie temp = cookies[i];
			if (temp.getName().equals("cookietest")) {
				cookie_response = temp;
				break;
			}
		}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	out.println("��ǰ��ʱ�䣺" + new java.util.Date() + "<br>");
	//������ǵ�һ�η��ʣ���ʾCookie�����ʱ��
	if (cookie_response != null) {
		out.println(cookie_response.getName() + "��һ�η��ʵ�ʱ�䣺"
		+ cookie_response.getValue());
		cookie_response.setValue(new Date().toString());
	}
	//����ÿͻ���һ�η��ʴ�ҳ�������еĲ���
	else {
		out.print("��һ�η���!");
		cookie_response = new Cookie("cookietest", new java.util.Date()
		.toString());
		out.print("����Cookie!");
	}
	//����Cookie������
	response.addCookie(cookie_response);
	response.setContentType("text/html");
	response.flushBuffer();
%>
