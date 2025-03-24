package com.starwars.starwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.starwars.starwars.clients")
public class StarwarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarwarsApplication.class, args);
	}

}
