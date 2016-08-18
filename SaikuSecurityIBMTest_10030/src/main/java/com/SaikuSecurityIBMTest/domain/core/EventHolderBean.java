package com.SaikuSecurityIBMTest.domain.core;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EventHolderBean {
	  @Autowired
	  private Environment saikuProperties;
	  
	  
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
    				
    				String loginUrl = saikuProperties.getProperty("saiku.session");//"http://localhost:8080/saiku/rest/saiku/session";
    			//	File file = new File("C://cool.xml");
    				System.err.println(" cool.xml file from c:drive....."); 
    				// First login. Take the cookies
    				Connection.Response res = Jsoup
    						.connect(loginUrl)
    						.data("username", saikuProperties.getProperty("saiku.adminusername"))
    						.data("password", saikuProperties.getProperty("saiku.adminpassword"))
    						.header("content-type", "application/x-www-form-urlencoded")
    						.referrer(saikuProperties.getProperty("saiku.referrer"))
    						.userAgent(saikuProperties.getProperty("saiku.useragent"))
    						.method(Method.POST).timeout(10000).execute();
    				
    				postSchemaResponse = res;
    				final Map<String, String> cookies = res.cookies();
    				System.out.println(cookies);
    				List<String> a = new ArrayList<String>();
    				a.add("ROLE_USER");
    				JSONObject testJson1 = new JSONObject();

    				testJson1.put("password",saikuProperties.getProperty("saiku.saikupassword"));
    				testJson1.put("email",saikuProperties.getProperty("saiku.saikuemail"));
    				testJson1.put("roles",a);
    				testJson1.put("username",saikuProperties.getProperty("saiku.saikuusername"));
    				System.err.println(" processing....."); 
    				
    				Response document = Jsoup
    						.connect(saikuProperties.getProperty("saiku.loginurl"))
    						.header("Content-Type", "application/json")
    						.requestBody(testJson1.toString())
    						.data("payload",testJson1.toString())
    						.ignoreContentType(true)
    						.referrer(saikuProperties.getProperty("saiku.referrer"))
    						.cookie("JSESSIONID", res.cookie("JSESSIONID"))
    						.userAgent(saikuProperties.getProperty("saiku.useragent"))
    						.method(Method.POST).timeout(10000).execute();
    				System.err.println("post successfully....."+ testJson1.toString() +"docx---"+document.url()); 
    			} catch (Exception e) {
    				System.err.println("Error" + e);

    			}	
    }
    
    
    public void postSchema() throws FileNotFoundException, IOException{
    	System.out.println("\n saiku file location---"+saikuProperties.getProperty("file.location"));
    	File file = new File(saikuProperties.getProperty("file.location"));
    	
    	// For Posting Schema into the server
    	//System.out.printf("file--name----",file.getName());(file.getName()).split(".")[0]
    	postDataSourceResponse = postSchemaResponse;
		Response document = Jsoup
				.connect(saikuProperties.getProperty("saiku.postschema"))
				.data("file", file.getName(), new FileInputStream(file))
				.data("name","cool" )
				.ignoreContentType(true)
				.referrer(saikuProperties.getProperty("saiku.referrer"))
				.cookie("JSESSIONID", postSchemaResponse.cookie("JSESSIONID"))
				.userAgent(saikuProperties.getProperty("saiku.useragent"))
				.method(Method.POST).timeout(10000).execute();
		System.err.println("Schema---post-- successfully....." + document.url());


    }
    
    @SuppressWarnings("unchecked")
	public void postDataSource() throws IOException{

		// JSON Object
    	JSONObject testJson = new JSONObject();
    	System.out.println("\n saiku file location---"+saikuProperties.getProperty("saiku.driver")
    	+"\n url--"+saikuProperties.getProperty("saiku.jdbcurl")+"\n schema--"+saikuProperties.getProperty("saiku.schema"));

		testJson.put("connectionname", saikuProperties.getProperty("saiku.connectionname"));
		testJson.put("jdbcurl", saikuProperties.getProperty("saiku.jdbcurl"));
		testJson.put("schema", saikuProperties.getProperty("saiku.schema"));
		testJson.put("driver", saikuProperties.getProperty("saiku.driver"));
		testJson.put("username",saikuProperties.getProperty("saiku.username"));
		testJson.put("password", saikuProperties.getProperty("saiku.password"));
		testJson.put("connectiontype", saikuProperties.getProperty("saiku.connectiontype"));
		
		System.out.println("Json Object constructed .....!!!- "+ testJson.toJSONString());
		// For Posting data source into server
		Response document = Jsoup
				.connect(saikuProperties.getProperty("saiku.postdatasource"))
				.header("Content-Type", "application/json")
				.requestBody(testJson.toString())
				.data(testJson)
				.ignoreContentType(true)
				.referrer(saikuProperties.getProperty("saiku.referrer"))
				.cookie("JSESSIONID", postDataSourceResponse.cookie("JSESSIONID"))
				.userAgent(saikuProperties.getProperty("saiku.useragent"))
				.method(Method.POST).timeout(10000).execute();
		System.out.println("postDataSource successfully.....!!!- "+ testJson.toJSONString()+"----"+document.url());
    }
}