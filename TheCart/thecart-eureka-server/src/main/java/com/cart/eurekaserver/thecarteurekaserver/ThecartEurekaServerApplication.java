package com.cart.eurekaserver.thecarteurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ThecartEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThecartEurekaServerApplication.class, args);
	}

}
