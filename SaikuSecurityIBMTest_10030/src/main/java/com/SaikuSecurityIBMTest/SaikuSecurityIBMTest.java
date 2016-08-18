package com.SaikuSecurityIBMTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.SaikuSecurityIBMTest.domain.core.EventHolderBean;

@ComponentScan({"geppetto.module.bootsecurity.*","com.SaikuSecurityIBMTest.*"})
@SpringBootApplication
public class SaikuSecurityIBMTest extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SaikuSecurityIBMTest.class);
    }

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ConfigurableApplicationContext ctx=SpringApplication.run(SaikuSecurityIBMTest.class, args);
		EventHolderBean u=ctx.getBean(EventHolderBean.class);
		u.setlogin();
		System.err.println("User SignUp Succesfull ~~~~~~~~ !!!!");
		u.postSchema(); 
		System.err.println("Schema posted successfully ~~~~~~~~~~!!!!");
		u.postDataSource();
		System.err.println("DataSource posted successfully ~~~~!!!");
	}
}
