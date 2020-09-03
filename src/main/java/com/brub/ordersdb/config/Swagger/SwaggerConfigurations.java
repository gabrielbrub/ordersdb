package com.brub.ordersdb.config.Swagger;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
public class SwaggerConfigurations {
	
	@Bean
	public Docket authToken() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("others")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.brub.ordersdb"))
				.paths(Predicates.not(PathSelectors.regex("(/auth).*"))) // ant("/**")
				.build()
				.ignoredParameterTypes(com.brub.ordersdb.model.User.class)
				.globalOperationParameters(Arrays.asList(
						new ParameterBuilder()
						.name("Authorization")
						.description("Header para token JWT")
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.required(false)
						.build()));
	}

	@Bean
	public Docket authPassword() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("auth")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.brub.ordersdb"))
				.paths(PathSelectors.regex("(/auth).*"))
				.build();
	}

}
