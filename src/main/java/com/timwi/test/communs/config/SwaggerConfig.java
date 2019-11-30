package com.timwi.test.communs.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.timwi.test"))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());                                           
    }
    
	private ApiInfo apiInfo() {
        return new ApiInfo(
          "TIMWI CONSULTING TEST", 
          "PROJECT USING SPRING BOOT & JAVA 8", 
          null, null, new Contact("Anass LAMKHAYAR", "https://github.com/anasslam/timwiTest",
        		  "anass.lamkhayar@neo-soft.fr"), null, null, Collections.emptyList());
    }
}
