package com;

import java.io.*;
import java.util.*;
import javax.servlet.*;

/**
 * ��ȡ������Ϣ��Servlet
 */
public class ServletInfo extends GenericServlet {

	private Map initParams = new LinkedHashMap();
	private String servletName = null;

	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		// ��ó�ʼ���������Ƽ���
		Enumeration paramNames = getInitParameterNames();
		// ������в����ĳ�ʼֵ
		while (paramNames.hasMoreElements()) {
			String name = (String) paramNames.nextElement();
			// ����������ò����ĳ�ʼֵ
			String value = getInitParameter(name);
			initParams.put(name, value);
		}

		// ���Servlet������
		servletName = getServletName();
	}

	public void service(ServletRequest request, ServletResponse response)
	throws ServletException, IOException {
		
		response.setContentType("text/html;charset=GB2312");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title>��ȡServlet�������Ϣ</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>Servlet�������Ϣ��</h2>");
		out.println("<h4>�������ƣ�" + servletName + "</h4><br>");
		out.println("<h4>��ʼ����:</h4>");
		out.println("<table width=\"350\" border=\"1\">");
		out.println("<tr>");
		out.println("<td width=\"175\">������</td>");
		out.println("<td width=\"175\">����ֵ</td>");
		out.println("</tr>");

		Set paramNames = initParams.keySet();
		Iterator iter = paramNames.iterator();
		while (iter.hasNext()) {

			String name = (String) iter.next();
			String value = (String) initParams.get(name);

			out.println("<tr>");
			out.println("<td>" + name + "</td>");
			out.println("<td>" + value + "</td>");
			out.println("</tr>");

		}

		out.println("</table>");	
		out.println("</body>");
		out.println("</html>");
	}

}