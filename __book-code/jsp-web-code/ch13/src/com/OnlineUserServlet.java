package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OnlineUserServlet extends HttpServlet
{    	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException,IOException
	{
		req.setCharacterEncoding("gb2312");
		String name=req.getParameter("user");
		String pwd=req.getParameter("password");

		if(null==name || null==pwd || name.equals("") || pwd.equals(""))
		{
			resp.sendRedirect("login.html");
		}
		else
		{
			HttpSession session=req.getSession();
			User user=(User)session.getAttribute("user");
			if(null==user || !name.equals(user.getName()))
			{
				user=new User(name);
				session.setAttribute("user",user);
			}

			resp.setContentType("text/html;charset=gb2312");
			PrintWriter out=resp.getWriter();

			out.println("��ӭ�û�<b>"+name+"</b>��¼<br>");
			UserList ul=UserList.getInstance();
			out.println("<br>��ǰ���ߵ��û��б�<br>");
			Enumeration<String> enums=ul.getUserList();
			int i=0;
			while(enums.hasMoreElements())
			{
				out.println(enums.nextElement());
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;");
				if(++i==10)
				{
					out.println("<br>");
				}
			}
			out.println("<br>��ǰ���ߵ��û�����"+i+"<br>");
			out.println("<p><a href=logout>�˳���¼</a>");
			out.close();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException,IOException
	{
		doGet(req,resp);
	}
}