package geppetto.module.bootsecurity.service;

/*import geppetto.GreenSnake.app.domain.core.GpUser;*/
import geppetto.module.bootsecurity.dao.GpBootSecurityUserDao;


import com.SaikuSecurityIBMTest.domain.core.GpAuthority;
import com.SaikuSecurityIBMTest.domain.core.GpUser;
import geppetto.module.bootsecurity.iservices.IGpUserService;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class GpBootSecurityUserService implements  IGpUserService {

	@Autowired
	private GpBootSecurityUserDao gpuser_Dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("............"+username);

		GpUser user = gpuser_Dao.findUser(username);
		if (user == null) {
			throw new UsernameNotFoundException("No User found");
		}
		System.out.println("-=--------------user----"+user.toString());
		return new User(user.getUsername(),user.getPassword(),user.getAuthorities());

	}

	public GpUser authenticateSocialUser(GpUser gpuser, HttpServletRequest request) throws Exception{

		Set<GpAuthority> authorities = new HashSet<GpAuthority>();
		GpAuthority authority = new GpAuthority();
		authority.setAuthority("GpUser");
		authorities.add(authority);
		gpuser.setRoles(authorities);

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				gpuser.getUsername(), gpuser.getPassword(),
				AuthorityUtils.createAuthorityList(gpuser.getAuthorities()
						.toString()));

		SecurityContext securityContext = SecurityContextHolder
				.getContext();
		securityContext.setAuthentication(authentication);

		// Create a new session and add the security context.
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

		GpUser user = gpuser_Dao.findUser(gpuser.getUsername());
		if (user == null) {
			//create new user
				user = new GpUser();
				user.setUsername(gpuser.getUsername());
				user.setPassword("password");
				user = gpuser_Dao.insert(user);

		}else{
			user = gpuser;
		}

		return user;

	}
}
