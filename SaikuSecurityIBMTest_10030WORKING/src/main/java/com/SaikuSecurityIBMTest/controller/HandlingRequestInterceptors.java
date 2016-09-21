/*package com.SaikuSecurityIBMTest.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class HandlingRequestInterceptors extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
	
		Collection<String> responseHeaders = response.getHeaderNames();
		System.out.println("responseHeaders data =>"+responseHeaders);
		
		StringBuffer requestUrl = request.getRequestURL();
		System.err.println("request Url string buffer =>"+requestUrl);
		
	   String ss = request.getRequestURI();
	   System.err.println("ss URI=>"+ss.toString());
		
		String sessionId = response.getHeader("Set-Cookie");
		System.err.println("Session Id =>"+sessionId);
		
		request.setAttribute("Cookie",sessionId);
		response.sendRedirect("http://localhost:8080/SaikuIBMTest_10030/login/loginSuccess");
		//return super.preHandle(request, response, handler);
		return false;
	}
	

}
*/