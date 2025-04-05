package com.sanvi.sanvi_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("dev")
public class SanviApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanviApiApplication.class, args);
	}

}
