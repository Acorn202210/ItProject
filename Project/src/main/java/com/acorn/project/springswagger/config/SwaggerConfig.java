package com.acorn.project.springswagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sun.tools.javac.util.List;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	private static final String REFERENCE = "Authorization 헤더 값";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.acorn.project.springswagger.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(true)
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(bearerAuthsSecurityScheme()));
    }
    
    private SecurityContext securityContext() {
    	return springfox.documentation
    			.spi.service.contexts
    			.SecurityContext
    			.builder()
    			.securityReferences(defaultAuth())
    			.operationSelector(OperationContext -> true)
    			.build();
    			
    }
    
    private List<SecurityReference> defaultAuth(){
    	AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    	authorizationScopes[0] = new AuthorizationScope("global", "accessEverything");
    	return List.of(new SecurityReference(REFERENCE, authorizationScopes));
    }
    
    private HttpAuthenticationScheme bearerAuthsSecurityScheme() {
    	return HttpAuthenticationScheme.JWT_BEARER_BUILDER
    			.name(REFERENCE).build();
    }

}