package geppetto.module.bootsecurity.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class RedirectingAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	//protected Log logger = LogFactory.getLog(this.getClass());
	List<String> userLoggedInfo = new ArrayList<String>(); 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            //logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            System.out.println("Response has already been committed. Unable to redirect to " + targetUrl);
        	return;
        }
        System.out.println("Request data in =>"+request.toString());
        System.out.println("Response data =>"+response.toString());
        System.out.println("authentication data =>"+authentication.toString());
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println("authorities data =>"+authorities.toString());
        for (GrantedAuthority grantedAuthority : authorities) {
        	System.out.println("grantedAuthority.getAuthority() =>"+grantedAuthority.getAuthority());
        	
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                isUser = true;
                userLoggedInfo.add(grantedAuthority.getAuthority());
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                userLoggedInfo.add(grantedAuthority.getAuthority());
                break;
            }
        }
 
        if (isUser) {
            return "/#/home";
        } else if (isAdmin) {
            return "/#/home";
        } else {
            throw new IllegalStateException();
        }
    }
    public List<String> getdata(){
    	List<String> data = new ArrayList<String>();
    	data.addAll(userLoggedInfo);
    	System.out.println("Inside regHandler user dta =>"+data.toString());
    	return data;
    }
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
	
    
	/*private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String url = request.getHeader("Referer");
        if (url != null) {
            String query = new URL(url).getQuery();
            if (query != null) {
                for (String nameValueStr : query.split("&")) {
                    String[] nameValue = nameValueStr.split("=");
                    if (nameValue[0].equals("redirect-to")) {
                        redirectStrategy.sendRedirect(request, response,
                                URLDecoder.decode(nameValue[1], "utf-8"));
                        return;
                    }
                }
            }
        }
        System.err.println("req"+request.toString());
        System.err.println("respo"+response.toString());
        System.err.println("authe"+authentication.toString());
        super.onAuthenticationSuccess(request, response, authentication);
    }*/
}
