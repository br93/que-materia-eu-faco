package br.com.quemateria.doc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {      
	
	public static final String AUTHORIZATION_HEADER = "Authorization";

	private Contact contato() {
		return new Contact("Seu nome", "http://www.seusite.com.br", "voce@seusite.com.br");
	}

	private ApiInfoBuilder informacoesApi() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

		apiInfoBuilder.title("Title - Rest API");
		apiInfoBuilder.description("API exemplo de uso de Springboot REST API");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Termo de uso: Open Source");
		apiInfoBuilder.license("Licença - Sua Empresa");
		apiInfoBuilder.licenseUrl("http://www.seusite.com.br");
		apiInfoBuilder.contact(this.contato());

		return apiInfoBuilder;

	}

	@Bean
	public Docket detalheApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.select().apis(RequestHandlerSelectors.basePackage("br.com.quemateria.controllers")).paths(PathSelectors.any()).build()
				.apiInfo(this.informacoesApi().build()).securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.consumes(new HashSet<String>(Arrays.asList("application/json")))
				.produces(new HashSet<String>(Arrays.asList("application/json")));

		return docket;
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
}