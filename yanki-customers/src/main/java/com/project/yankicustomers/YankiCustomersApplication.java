package com.project.yankicustomers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class YankiCustomersApplication {

	public static void main(String[] args) {
		SpringApplication.run(YankiCustomersApplication.class, args);
	}

}
