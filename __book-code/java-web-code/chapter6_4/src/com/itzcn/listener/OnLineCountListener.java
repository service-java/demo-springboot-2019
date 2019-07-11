package com.itzcn.listener;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnLineCountListener implements ServletContextListener, HttpSessionListener,
		HttpSessionAttributeListener {
	// ����һ��ServletContext����
	private ServletContext application = null;
	private ArrayList<String> users = null;
	private HttpSession session = null;
	private String user = null;
	//context��ʼ��ʱ����
	public void contextInitialized(ServletContextEvent sce) {
		// ������ʼ��ʱ����application�д��һ���յ�����
		this.application = sce.getServletContext();
		this.application.setAttribute("users", new ArrayList<String>());
		
	}
	//contextɾ��ʱ����
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	public void sessionCreated(HttpSessionEvent se) {
	}
	public void sessionDestroyed(HttpSessionEvent se) {		
		session = se.getSession();    
		user = (String)session.getAttribute("username");    
       users = (ArrayList<String>)session.getServletContext().getAttribute("users");  
        for(String u:users){ 
            //������û���ServletContext�������Ƴ� 
            if(u.equals(user)){   
                users.remove(u);      
                break;   
            }   
        }   
        //��session���ó���Ч  
        session.invalidate();   
        System.out.println("һ��Session��������!"); 
	}
	public void attributeAdded(HttpSessionBindingEvent se) {
		// �����½�ɹ������û����������б�֮��
		users=(ArrayList<String>)application.getAttribute("users");
		users.add(se.getValue().toString());
		this.application.setAttribute("users", users);
	}
	//ɾ��һ���µ�����ʱ����
	public void attributeRemoved(HttpSessionBindingEvent se) {
	}
	//���Ա����ʱ����
	public void attributeReplaced(HttpSessionBindingEvent se) {
	}
}
