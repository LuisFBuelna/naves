package com.buelna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NavesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NavesApplication.class, args);
	}

}
