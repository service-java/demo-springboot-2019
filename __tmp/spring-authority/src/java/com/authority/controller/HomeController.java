package com.authority.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.authority.model.SysUsers;
import com.authority.utils.StringUtil;

/**
 * 
 * @author Tony
 * 
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletResponse response, HttpServletRequest request) {
		boolean isAjax = "XMLHttpRequest".equals(request
				.getHeader("X-Requested-With"));
		// 如果是ajax请求
		if (isAjax) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		return "common/login";
	}
	
	   @RequestMapping(value = "/home",method = RequestMethod.GET)
	    public String toMainPage(Map model,HttpSession session){
	        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        if(!StringUtil.isNotEmpty(user)){
	        	return "common/login";
	        }
	        if(user != null && user instanceof SysUsers){
	            SysUsers user1 = (SysUsers) user;
	            model.put("username",user1.getUserName());
	        }
	        return "common/Home";
	   }
	        
	   @RequestMapping(value = "/forbidden")
	    public String Forbidden(HttpServletRequest request,HttpServletResponse response){
	        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	        //如果是ajax请求
	        if (isAjax) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        }
	        return "common/403";
	    }
	    @RequestMapping(value = "/maxSessionError")
	    public String MaxSessionError(HttpServletRequest request,HttpServletResponse response){
	        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	        //如果是ajax请求
	        if (isAjax) {
	            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
	        }
	        return "common/max_session_error";
	    }
	    
	

}
