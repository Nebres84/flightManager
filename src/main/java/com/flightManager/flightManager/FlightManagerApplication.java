package com.flightManager.flightManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@ComponentScan
public class FlightManagerApplication {

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   //set application UTC timezone
		System.out.println("Spring boot application running in UTC timezone :" + new Date());
	}

	public static void main(String[] args) {
		SpringApplication.run(FlightManagerApplication.class, args);
	}

}
