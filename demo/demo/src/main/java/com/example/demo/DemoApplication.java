package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import java.io.ObjectInputFilter;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class DemoApplication {

	public static void main(String[] args) {
		/* AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(ObjectInputFilter.Config.class); */
		SpringApplication.run(DemoApplication.class, args);
	}

}
