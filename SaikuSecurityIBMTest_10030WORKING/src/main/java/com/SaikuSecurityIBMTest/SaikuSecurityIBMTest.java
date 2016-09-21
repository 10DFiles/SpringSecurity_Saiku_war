package com.SaikuSecurityIBMTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.json.simple.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import com.SaikuSecurityIBMTest.controller.SimpleCORSFilter;


@ComponentScan({"geppetto.module.bootsecurity.*","com.SaikuSecurityIBMTest.*"})
@SpringBootApplication
public class SaikuSecurityIBMTest extends SpringBootServletInitializer {
	
	
	
	  //@Autowired
	   Environment saikuProperties ;//= new Environment();
	  
	  
  private Boolean eventFired = false;
  
	
	Connection.Response postSchemaResponse;
	Connection.Response postDataSourceResponse;

  public Boolean getEventFired() {
      return eventFired;
  }

  public void setEventFired(Boolean eventFired) {
      this.eventFired = eventFired;
  }
  
  @SuppressWarnings("unchecked")
	public void setlogin (){
  	  // TODO Auto-generated method stub
  			JSONObject jsonObject = null;
  			try {
  				
  				//String loginUrl = saikuProperties.getProperty("saiku.session");//"http://localhost:8080/saiku/rest/saiku/session";
  			//	File file = new File("C://cool.xml");
  				System.err.println(" cool.xml file from c:drive....."); 
  				// First login. Take the cookies
  				Connection.Response res = Jsoup
  						.connect("http://52.88.1.3:8080/saiku/rest/saiku/session")
  						.data("username","admin")
  						.data("password", "admin")
  						.header("content-type", "application/x-www-form-urlencoded")
  						.referrer("http://52.88.1.3:8080/")
  						.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
  						.method(Method.POST).timeout(10000).execute();
  				
  				postSchemaResponse = res;
  				final Map<String, String> cookies = res.cookies();
  				System.out.println(cookies);
  				List<String> a = new ArrayList<String>();
  				a.add("ROLE_USER");
  				JSONObject testJson1 = new JSONObject();

  				testJson1.put("password","user");
  				testJson1.put("email","user@10decoders.in");
  				testJson1.put("roles",a);
  				testJson1.put("username","user");
  				System.err.println(" processing....."); 
  				
  				Response document = Jsoup
  						.connect("http://52.88.1.3:8080/saiku/rest/saiku/admin/users/")
  						.header("Content-Type", "application/json")
  						.requestBody(testJson1.toString())
  						.data("payload",testJson1.toString())
  						.ignoreContentType(true)
  						.referrer("http://52.88.1.3:8080/")
  						.cookie("JSESSIONID", res.cookie("JSESSIONID"))
  						.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
  						.method(Method.POST).timeout(10000).execute();
  				System.err.println("post successfully....."+ testJson1.toString() +"docx---"+document.url()); 
  			} catch (Exception e) {
  				System.err.println("Error" + e);

  			}	
  }
  
  
  public void postSchema() throws FileNotFoundException, IOException{
  	System.out.println("\n saiku file location---/cool.xml");//+saikuProperties.getProperty("file.location"));
  	File file = new File("/cool.xml");
  	
  	// For Posting Schema into the server
  	//System.out.printf("file--name----",file.getName());(file.getName()).split(".")[0]
  	postDataSourceResponse = postSchemaResponse;
		Response document = Jsoup
				.connect("http://52.88.1.3:8080/saiku/rest/saiku/admin/schema/drinks/")
				.data("file", file.getName(), new FileInputStream(file))
				.data("name","cool" )
				.ignoreContentType(true)
				.referrer("http://52.88.1.3:8080/")
				.cookie("JSESSIONID", postSchemaResponse.cookie("JSESSIONID"))
				.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
				.method(Method.POST).timeout(10000).execute();
		System.err.println("Schema---post-- successfully....." + document.url());


  }
  
  @SuppressWarnings("unchecked")
	public void postDataSource() throws IOException{

		// JSON Object
  	JSONObject testJson = new JSONObject();
  	/*System.out.println("\n saiku file location---"+saikuProperties.getProperty("saiku.driver")
  	+"\n url--"+saikuProperties.getProperty("saiku.jdbcurl")+"\n schema--"+saikuProperties.getProperty("saiku.schema"));
*/
		testJson.put("connectionname", "DrinksCube");
		testJson.put("jdbcurl", "jdbc:mysql://ibmsaiku.cogexg2v4iz2.us-west-2.rds.amazonaws.com:3306/drink");
		testJson.put("schema", "/datasources/cool.xml");
		testJson.put("driver", "com.mysql.jdbc.Driver");
		testJson.put("username","ibmsaiku");
		testJson.put("password", "ibmsaiku123");
		testJson.put("connectiontype", "MONDRIAN");
		
		System.out.println("Json Object constructed .....!!!- "+ testJson.toJSONString());
		// For Posting data source into server
		Response document = Jsoup
				.connect("http://52.88.1.3:8080/saiku/rest/saiku/admin/datasources/")
				.header("Content-Type", "application/json")
				.requestBody(testJson.toString())
				.data(testJson)
				.ignoreContentType(true)
				.referrer("http://52.88.1.3:8080/")
				.cookie("JSESSIONID", postDataSourceResponse.cookie("JSESSIONID"))
				.userAgent(" Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
				.method(Method.POST).timeout(10000).execute();
		System.out.println("postDataSource successfully.....!!!- "+ testJson.toJSONString()+"----"+document.url());
  }
	
////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	//SpringApplicationBuilder en = application.sources(EventHolderBean.class);
    	//ConfigurableApplicationContext ctx=SpringApplication.run(SaikuSecurityIBMTest.class);
    	//SaikuSecurityIBMTest u =ctx.getBean(SaikuSecurityIBMTest.class);
		//u.setlogin();
    	
    	////////////////////////////////////////////////////////////////////////////////////
    	setlogin();
		System.err.println("User SignUp Succesfull ~~~~~~~~ !!!!");
		try {
			//u.postSchema();
			postSchema();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.err.println("Schema posted successfully ~~~~~~~~~~!!!!");
		try {
			//u.postDataSource();
			postDataSource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println("DataSource posted successfully ~~~~!!!");
        return application.sources(SaikuSecurityIBMTest.class);
    }

	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.err.println("IN MAIN METHOD");
		/*ConfigurableApplicationContext ctx=*/SpringApplication.run(SaikuSecurityIBMTest.class, args);
	}
	@Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(simpleCORSFilter());
        registration.addUrlPatterns("/*");
        registration.setName("simpleCORSFilter");
        registration.setOrder(1);
        return registration;
    } 

    @Bean(name = "simpleCORSFilter")
    public Filter simpleCORSFilter() {
        return new SimpleCORSFilter();
    }
}
