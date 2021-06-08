package com.ml.mutante.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ml.mutante.controller"))
				.paths(PathSelectors.regex("/.*"))
				.build().apiInfo(metadata())
				.apiInfo(metadata())//
				.useDefaultResponseMessages(false);
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder()//
				.title("Prueba Tecnica Mercado Libre - Mutantes")//
				.description("Prueba Tecnica")//
				.version("1.0.0")//
				.license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
				.contact(new Contact(null, null, "julianhernandez87@gmail.com"))//
				.build();
	}

}