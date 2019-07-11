package com;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener,
		ServletContextAttributeListener {

	private ServletContext context = null;

    /**
     *���´���ʵ��ServletContextListener�ӿڡ�
     */
    public void contextDestroyed(ServletContextEvent sce) {
    	logout("contextDestroyed()-->ServletContext������");
    	this.context = null;

    }

    public void contextInitialized(ServletContextEvent sce) {
    	this.context = sce.getServletContext();
    	logout("contextInitialized()-->ServletContext��ʼ����");

    }//ServletContextListener

    /**
     *���´���ʵ�� ServletContextAttributeListener�ӿ�
     */    
    public void attributeAdded(ServletContextAttributeEvent scae) {
    	logout("������һ��ServletContext���ԣ�attributeAdded('" + scae.getName() + "', '" +
    			scae.getValue() + "')");

    }

    public void attributeRemoved(ServletContextAttributeEvent scae) {
    	logout("ɾ����һ��ServletContext���ԣ�attributeRemoved('" + scae.getName() + "', '" +
    			scae.getValue() + "')");

    }


    public void attributeReplaced(ServletContextAttributeEvent scae) {
    	logout("ĳ��ServletContext�����Ա��ı䣺attributeReplaced('" + scae.getName() + "', '" +
    			scae.getValue() + "')");

    }

    private void logout(String message) {		    
    	PrintWriter out=null;
    	try {
    		out=new PrintWriter(new FileOutputStream("c:\\test.txt",true));
    		out.println(new java.util.Date().toLocaleString()+"::Form ContextListener: " + message);
    		out.close();
    	}
    	catch(Exception e) {
    		out.close();
    		e.printStackTrace();
    	}

    }  

}
