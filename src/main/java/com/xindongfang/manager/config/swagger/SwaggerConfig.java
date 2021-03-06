package com.xindongfang.manager.config.swagger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket createRestApi() {
//    	  添加head参数配置start
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        tokenPar.name("time").description("时间").modelRef(new ModelRef("Long")).parameterType("query").required(false).build();
//        ParameterBuilder tokenPar1 = new ParameterBuilder();
//        tokenPar1.name("secret").description("密钥").modelRef(new ModelRef("String")).parameterType("query").required(false).build();
//        
//        List<Parameter> pars = new ArrayList<>();
//        pars.add(tokenPar.build());
//        pars.add(tokenPar1.build());

    	
        return new Docket(DocumentationType.SWAGGER_2)
        		
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xindongfang.manager.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.regex("^(?!auth).*$"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
//                .globalOperationParameters(pars)
                ;//注意这里;
    }
    
    private List<ApiKey> securitySchemes() {
    	ApiKey ak=new ApiKey("Authorization", "access_token", "header");
    	List<ApiKey> aks=new ArrayList<>();
    	aks.add(ak);
        return aks;
 }
    
    private List<SecurityContext> securityContexts() {
    	 SecurityContext build = SecurityContext.builder()
         .securityReferences(defaultAuth())
//         .forPaths(PathSelectors.regex("^(?!auth).*$"))
         .forPaths(PathSelectors.any())
         .build();
    	 List<SecurityContext> list=new ArrayList<>();
    	 list.add(build);
        return list;
    }
    
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        SecurityReference securityReference = new SecurityReference("Authorization", authorizationScopes);
        ArrayList<SecurityReference> arrayList = new ArrayList<>();
        arrayList.add(securityReference);
        return arrayList;
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("新东方api")
                .description("相关接口文档")
                .contact(new Contact("xindongfang", "http://www.baidu.com", ""))
                .version("2.0")
                .build();
    }



    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(null, "list", "alpha", "schema",
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
    }
}