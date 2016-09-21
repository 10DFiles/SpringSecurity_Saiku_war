package com.SaikuSecurityIBMTest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public ResponseWrapper create() {
		try {
			ResponseWrapper wrap = new ResponseWrapper();
			wrap.setResponseSuccess("success");
			return wrap;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
