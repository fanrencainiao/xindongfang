package com.xindongfang.manager;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextListener;





@SpringBootApplication
@EntityScan("com.xindongfang.manager.entity")
@ComponentScan(basePackages ="com.xindongfang.manager")
//@EnableConfigurationProperties(value = { AuthorizationFilterProperties.class, SmsConfig.class,AliPayConfig.class,WxConfig.class})
@ServletComponentScan
public class XinDongFangApplication {

	public static void main(String[] args) {
		SpringApplication.run(XinDongFangApplication.class, args);
	}
	
	
	@Bean
	public RequestContextListener requestContextListener(){
		    return new RequestContextListener();
		} 

}
