<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
pageContext�Ĳ���ҳ�棭��pagecontext������һЩ���ԣ�
<br>
<%
	ServletRequest req = pageContext.getRequest();//
	String name = req.getParameter("name");//��request.getParameter("name")Ч����һ����
	out.println("request�ĵ��Ĳ���:name=" + name);
	pageContext.setAttribute("userName", name);
	pageContext.getSession().setAttribute("sessionValue", name);
	pageContext.getServletContext().setAttribute("sharevalue", name);
	out.println("<br>pageContext.getAttribute('userName'):");
	out.println(pageContext.getAttribute("userName"));//ֻ�ڵ�ǰ��ҳ����Ч
	out.println("<br>pageContext.getSession().getAttribute('sessionValue')=");
	out.println(pageContext.getSession().getAttribute("sessionValue"));
	out.println("<br>pageContext.getServletContext().getAttribute('sharevalue')=");
	out.println(pageContext.getServletContext().getAttribute("sharevalue"));
%>
<a href="pagecontext2.jsp">next--></a>��ת����һ������ҳ��
