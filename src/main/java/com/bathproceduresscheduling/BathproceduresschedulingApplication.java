package com.bathproceduresscheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.bathproceduresscheduling"})
@EnableAutoConfiguration
public class BathproceduresschedulingApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationArguments.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BathproceduresschedulingApplication.class, args);
		
		LOGGER.info("BathproceduresschedulingApp is STARTED!");
	}

}
