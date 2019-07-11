package com;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: RequestServlet
 */
 public class HttpReqResServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("gb2312");
		response.setContentType("text/html;charset=gb2312");
		
		PrintWriter out = response.getWriter();
		out.println("<h3><br>�ͻ�ʹ�õ�Э����:");
			   out.println("request.getProtocol()");

			out.println("<br>��ȡ���ܿͻ��ύ��Ϣ��ҳ�棺");
			   out.println(request.getServletPath());

			out.println("<br>���ܿͻ��ύ��Ϣ�ĳ��ȣ�");
			   out.println(request.getContentLength());

			out.println("<br>�ͻ��ύ��Ϣ�ķ�ʽ��");
			   out.println(request.getMethod());

			out.println("<br>��ȡHTTPͷ�ļ���User-Agent��ֵ��");
			   out.println(request.getHeader("User-Agent"));

			out.println("<br>��ȡHTTPͷ�ļ���Host��ֵ��");
			   out.println(request.getHeader("Host"));

			out.println("<br>��ȡHTTPͷ�ļ���accept��ֵ��");
			   out.println(request.getHeader("accept"));

			out.println("<br>��ȡHTTPͷ�ļ���accept-encoding��ֵ��");
			   out.println(request.getHeader("accept-encoding"));

			out.println("<br>��ȡ�ͻ��������ƣ�");
			   out.println(request.getRemoteHost()); 

			out.println("<br>��ȡ�ͻ���IP��ַ��");
			   out.println(request.getRemoteAddr());

			out.println("<br>��ȡ�����������ƣ�");
			   out.println(request.getServerName());

			out.println("<br>��ȡ�������Ķ˿ںţ�");
			   out.println(request.getServerPort()); 

			out.println("<br>��ǰʱ�䣺");
			
			out.println(new Date());
           //	5���ֺ��Զ�ˢ�±�ҳ��
			response.setHeader("Refresh","5");
			out.println("</h3>");
			  
			  
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}   	  	    
}