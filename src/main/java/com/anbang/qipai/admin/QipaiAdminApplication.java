package com.anbang.qipai.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.anbang.qipai.admin.cqrs.c.repository.SingletonEntityFactoryImpl;
import com.dml.users.UserSessionsManager;
import com.highto.framework.ddd.SingletonEntityRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableScheduling
public class QipaiAdminApplication {

	@Bean
	public SingletonEntityRepository singletonEntityRepository() {
		SingletonEntityRepository singletonEntityRepository = new SingletonEntityRepository();
		singletonEntityRepository.setEntityFactory(new SingletonEntityFactoryImpl());
		return singletonEntityRepository;
	}

	@Bean
	public UserSessionsManager userSessionsManager() {
		return new UserSessionsManager();
	}

	public static void main(String[] args) {
		SpringApplication.run(QipaiAdminApplication.class, args);
	}
}
