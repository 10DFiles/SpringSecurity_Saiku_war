package geppetto.module.bootsecurity.config;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.context.request.RequestContextHolder;

public class RedirectingAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	String targetUrl = "http://52.88.1.3:8080/SaikuIBMTest_10030/login/loginSuccess/";

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		String referer = request.getHeader("Referer");
		System.err.println("Referer data =>"+referer);
		if(referer != null){
			System.err.println("Inside the data of referer");
			if (referer.endsWith("/")) {
				referer = referer.substring(0, referer.length() - 1);
				System.err.println("referer data after remove slash=>"+referer);
				response.setHeader("Access-Control-Allow-Origin", referer);
			}
		}
		return targetUrl;
		/*response.setHeader("Access-Control-Allow-Origin", "http://localhost:8100");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Access-Control-Allow-Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");*/
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String currentCookie = RequestContextHolder.currentRequestAttributes().getSessionId();
		System.err.println("current cookie data of server=>" + currentCookie);
		String url = request.getHeader("Referer");
		String sessionData = request.getHeader("Cookie");
		System.out.println("response data =>" + response);
		System.out.println("authentication data =>" + authentication);
		super.onAuthenticationSuccess(request, response, authentication);
		System.err.println("resposnse header data=>"+response.getHeaderNames());
		System.err.println("session Data of =>" + sessionData);
		System.out.println("url data =>" + url.toString());
		 //request.setAttribute("Cookie", currentCookie);
		/*
		 * String sessionDays = request.getHeader("Cookie"); System.err.println(
		 * "redefined data =>"+sessionDays);
		 */

		// response.sendRedirect(targetUrl);
		/*
		 * if (url != null) { String query = new URL(url).getQuery();
		 * 
		 * if (query != null) { System.out.println(
		 * "query data security config=>"+query.toString());
		 * 
		 * for (String nameValueStr : query.split("&")) { System.out.println(
		 * "nameValueStr data=>"+nameValueStr); String[] nameValue =
		 * nameValueStr.split("="); if (nameValue[0].equals("redirect-to")) {
		 * redirectStrategy.sendRedirect(request, response,
		 * URLDecoder.decode(nameValue[1], "utf-8")); System.out.println(
		 * "redirect data =>"+redirectStrategy); System.out.println(
		 * "nameValue[1] data=>"+nameValue[1]); return; } } } }
		 */
		/*String scheme = request.getScheme(); // http
		System.out.println("schema data=>" + scheme);
		String serverName = request.getServerName(); // hostname.com
		System.out.println("serverName data=>" + serverName);
		int serverPort = request.getServerPort(); // 80
		System.out.println("severport data=>" + serverPort);
		String contextPath = request.getContextPath(); // /mywebapp
		System.out.println("contextPath data =>" + contextPath);
		String servletPath = request.getServletPath(); // /servlet/MyServlet
		System.out.println("servletPath data=>" + servletPath);
		String pathInfo = request.getPathInfo(); // /a/b;c=123
		System.out.println("pathInfo data =>" + pathInfo);
		String queryString = request.getQueryString();
		System.out.println("queryString data =>" + queryString);*/
		/*
		 * System.out.println("request whole data =>"+request);
		 * System.out.println("request data getRequest Uri=>"
		 * +request.getRequestURI());
		 */
	}

}
