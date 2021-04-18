package com.capstone.fans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class FansApplication {
	public static void main(String[] args){
		SpringApplication.run(FansApplication.class, args);
	}
}
