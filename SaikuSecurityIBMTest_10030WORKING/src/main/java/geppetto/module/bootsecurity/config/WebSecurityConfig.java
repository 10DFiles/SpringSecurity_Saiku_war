package geppetto.module.bootsecurity.config;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

/*import com.SaikuSecurityIBMTest.controller.HandlingRequestInterceptors;*/
import com.google.common.collect.ImmutableMap;

import geppetto.module.bootsecurity.service.GpBootSecurityUserService;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	private GpBootSecurityUserService userDetailsService;

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/user/createsocialuser");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ExceptionMappingAuthenticationFailureHandler loginFailureHandler = new ExceptionMappingAuthenticationFailureHandler();
        loginFailureHandler.setDefaultFailureUrl("/login/loginFailure");
		http
        .authorizeRequests()
		.regexMatchers("/admin.*").hasRole("ADMIN")
        .regexMatchers("/user.*").hasRole("USER")
        	.and()
        	.formLogin()
        	.usernameParameter("j_username") 
        	.passwordParameter("j_password")
        	.loginProcessingUrl("/j_spring_security_check")
        	//.defaultSuccessUrl("/login/loginSuccess")
        	.successHandler(new RedirectingAuthenticationSuccessHandler())
        	.permitAll()
        	.failureHandler(loginFailureHandler)
        	.permitAll().and().logout().permitAll()
        	.and().exceptionHandling()
        	.accessDeniedPage("/accessDenied/403").and().csrf().disable();
	}

	
}