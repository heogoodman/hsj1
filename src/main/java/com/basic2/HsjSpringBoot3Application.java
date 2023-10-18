package com.basic2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HsjSpringBoot3Application {

	public static void main(String[] args) {
//		SpringApplication.run(HsjSpringBoot3Application.class, args);


		SpringApplication application = new SpringApplication(HsjSpringBoot3Application.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}

}
