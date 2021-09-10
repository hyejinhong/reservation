package kr.or.connect.reservation.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.cglib.core.Predicate;
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
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("kr.or.connect.reservation.controller"))
				.paths(PathSelectors.any()).build();
	}
	
	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add("*/*");
		
		return produces;
	}

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("BoostCourse Reservation REST API")
				.description("It is a RESTful API for providing information by reservation.")
				.license("")
				.licenseUrl("http://unlicense.org")
				.termsOfServiceUrl("")
				.version("0.1")
				.contact(new Contact("", "", ""))
				.build();
	}
}
