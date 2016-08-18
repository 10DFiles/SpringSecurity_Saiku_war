package geppetto.module.bootsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import geppetto.module.bootsecurity.service.GpBootSecurityUserService;

@Configuration
@EnableWebMvcSecurity
/*@ComponentScan("geppetto")
@EntityScan("geppetto.module.bootsecurity.domain")*/
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
    private GpBootSecurityUserService userDetailsService;
	
/*	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
						"select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery(
						"select username, role from user_roles where username=?");
	}
*/
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/user/createsocialuser");
	    /*//regexMatchers("/shutdown").
        regexMatchers(".*\\.html").
        regexMatchers(".*\\.png").
        regexMatchers(".*\\.map").
        regexMatchers(".*\\.css");*/
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				//.regexMatchers("/app/**").permitAll()
				.antMatchers("/","/lib/**", "/app/**").permitAll()
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/#/login")
				.permitAll()
				//.defaultSuccessUrl("/#/login-success")
				.successHandler(new RedirectingAuthenticationSuccessHandler())
				.permitAll()
				.failureUrl("/#/login?error=true").permitAll().usernameParameter("username")
				.passwordParameter("password").and().logout().permitAll()
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/403").and().csrf().disable();
	
	/**
	 * http.
            addFilter(headerAdminFilter).
            addFilterBefore(new LoginAwaitEventFilter(catchUp, UserAccountConfiguration.NAME), UsernamePasswordAuthenticationFilter.class).
            authorizeRequests().
                regexMatchers("/xmla").hasIpAddress("127.0.0.0/8").
                regexMatchers("/login.*").permitAll().
                regexMatchers(HttpMethod.POST, "/api/client-error.*").permitAll().
                regexMatchers("/api.*").fullyAuthenticated().
                regexMatchers("/jolokia.*").hasRole(ADMINISTRATOR).
                regexMatchers("/appadmin.*").hasRole(ADMINISTRATOR).
                regexMatchers(".*").fullyAuthenticated().
        and().
            formLogin().
                loginPage("/login").
                successHandler(new RedirectingAuthenticationSuccessHandler()).
                failureHandler(loginFailureHandler).
        and().
            exceptionHandling().authenticationEntryPoint(new RestAwareAuthenticationEntryPoint("/login"));
	 */
	
	}

	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }
}