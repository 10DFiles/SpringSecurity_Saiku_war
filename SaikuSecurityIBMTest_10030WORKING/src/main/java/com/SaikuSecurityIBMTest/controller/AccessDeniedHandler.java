package com.SaikuSecurityIBMTest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accessDenied")
public class AccessDeniedHandler {

	@RequestMapping(value="/403",method = RequestMethod.GET)
	public  ResponseWrapper  create() {
		try {
			ResponseWrapper wrap = new ResponseWrapper();
				wrap.setResponseDenied("accessDenied");
				return wrap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
