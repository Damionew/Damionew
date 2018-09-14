package com.hi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class HiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiApplication.class, args);
	}
	
	@Value("${server.port}")
	String port;
	
	@RequestMapping("/hi")
	public String hi() {
		return "hi";
	}
}
