package com;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

public class SessionAttributeListener implements HttpSessionAttributeListener {
	public void attributeAdded(HttpSessionBindingEvent se) {

		try {
			FileWriter file = new FileWriter("c:\\SessionAttribute.txt", true);
			PrintWriter out = new PrintWriter(file);
			HttpSession session = se.getSession();
			out.println(new Date() + "   " + "�¼�һ��session����,ID="
					+ session.getId() + "   " + se.getName() + "="
					+ se.getValue());
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		
		try
		{			
		    FileWriter file=new FileWriter("c:\\SessionAttribute.txt",true);
   	 	    PrintWriter out=new PrintWriter(file);
   	 	HttpSession session = se.getSession();
   	    	out.println( new Date()+"  "+"ɾ��һ��session����,ID=" + session.getId()
   	    		         +"   "+se.getName() + "=" + se.getValue());
   	 	    out.close();
   	 	}catch(Exception e)
   	 	{
   	 		System.out.println(e);
   	 		}
		
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {

		try {
			FileWriter file = new FileWriter("c:\\SessionAttribute.txt", true);
			PrintWriter out = new PrintWriter(file);
			HttpSession session = se.getSession();
			out.println(new Date() + "  " + "�޸���һ��session����,ID="
					+ session.getId() + "   " + se.getName() + "="
					+ se.getValue());
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
