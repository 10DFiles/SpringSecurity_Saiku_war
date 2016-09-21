package com.SaikuSecurityIBMTest.controller;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
	@RequestMapping(value="/loginSuccess",method = RequestMethod.GET)
	public  ResponseWrapper  create(Principal principle) {	
		System.out.println("-------***************----------------"); 
		try {
			//System.out.println("-------*********in try******----------------"+principle.toString());
			ResponseWrapper wrap = new ResponseWrapper();
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			wrap.setResult(principal);
			wrap.setResponseSuccess("success");
			System.out.println("-------sucess********----------------");
			return wrap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value="/loginFailure",method = RequestMethod.GET)
	public  ResponseWrapper  loginFailure( ) {	
		System.out.println("-------***************----------------"); 
		try {
			ResponseWrapper wrap = new ResponseWrapper();
			wrap.setResponseError("loginFailed");
			System.out.println("-------login failure********----------------");
			return wrap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
