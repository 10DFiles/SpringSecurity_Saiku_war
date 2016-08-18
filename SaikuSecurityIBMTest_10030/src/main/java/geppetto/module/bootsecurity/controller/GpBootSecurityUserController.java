package geppetto.module.bootsecurity.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SaikuSecurityIBMTest.controller.GpBaseController;
import com.SaikuSecurityIBMTest.domain.core.GpUser;

import geppetto.module.bootsecurity.config.RedirectingAuthenticationSuccessHandler;
/*import geppetto.GreenSnake.app.domain.core.GpUser;*/
import geppetto.module.bootsecurity.service.GpBootSecurityUserService;

@Controller("UserController")
@RequestMapping("/user")
public class GpBootSecurityUserController {

	@Autowired
	GpBootSecurityUserService user_service;

	

	@RequestMapping(value="/createsocialuser",method = RequestMethod.POST)
	public @ResponseBody
	GpUser create(HttpServletRequest request,
			@RequestBody GpUser gpuser) {
		try {
			user_service.authenticateSocialUser(gpuser,request);
			return gpuser;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/getUserDetail", method=RequestMethod.GET)
	public  @ResponseBody UserDetails gettheUserLoggedInfo(){
		//RedirectingAuthenticationSuccessHandler reg = new RedirectingAuthenticationSuccessHandler();
		GpBaseController gbUd = new GpBaseController();
		UserDetails userD= gbUd.getUser();
		//System.out.println("GBUSER DATA IF GOT *****************"+userD.toString());
		//List<String> userLoggedInto = new ArrayList<String>();
		//userLoggedInto.add(userD.getUsername());
		/*if("ROLE_ADMIN".equals(userD.getAuthorities())){
			userLoggedInto.add("ROLE_ADMIN");
		}*/
		//System.out.println("data of userLoggedInto =>"+userLoggedInto.toString());
		/*if(userLoggedInto != null){
		return userLoggedInto;
		}else{
		userLoggedInto.add("null");
		}*/
		return userD; 
	}
	
}
