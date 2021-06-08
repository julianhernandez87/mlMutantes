package com.ml.mutante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = "com.ml.mutante")
@EntityScan("com.ml.mutante.model")
@EnableJpaRepositories("com.ml.mutante.repository")
@EnableAutoConfiguration
public class MutantApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantApplication.class, args);

	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
