package com;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpErrHandlerServlet extends HttpServlet
{
    protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, java.io.IOException
    {
        resp.setContentType("text/html;charset=GB2312");
        PrintWriter out = resp.getWriter();
        
        Integer status_code=(Integer)req.getAttribute("javax.servlet.error.status_code");
        
        out.println("<html><head><title>����ҳ��</title></head>");
        out.println("<body>");
        
        //������JDK�汾����1.5����ô��Ӧ�ð������·�ʽ����
        //int status=status_code.intValue();
        //switch(status){...}
        switch(status_code)
        {
        case 401:
            break;
        case 404:
            out.println("<h2>HTTP״̬���룺"+status_code+"</h2>");
            out.println("������������ҳ������Ѿ�ɾ������������ʱ�����á�");
            out.println("ת��<a href='mailto:admin@jsp.org'>��վ����Ա</a>����֧�֡�");
            break;
        case 500:
        	out.println("<h2>HTTP״̬���룺"+status_code+"</h2>");
        	out.println("The server encountered an internal error () that prevented it from fulfilling this request");
        	break;
        default:
            break;
        }
        out.println("</body></html>");
        out.close();
    }
}