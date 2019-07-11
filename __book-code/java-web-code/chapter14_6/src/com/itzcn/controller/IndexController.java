package com.itzcn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IndexController implements Controller {
	
	private String sayHello;//����sayHello����
	
	public String getSayHello() {
		return sayHello;
	}
	public void setSayHello(String sayHello) {
		this.sayHello = sayHello;
	}
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		return new ModelAndView("index","hello",sayHello);//����һ��ModelAndView����
	}

}
