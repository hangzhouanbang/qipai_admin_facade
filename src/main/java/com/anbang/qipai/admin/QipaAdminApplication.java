package com.anbang.qipai.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class QipaAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(QipaAdminApplication.class, args);
	}
}
