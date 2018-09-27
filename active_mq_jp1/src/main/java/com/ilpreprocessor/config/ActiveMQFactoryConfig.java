package com.ilpreprocessor.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class ActiveMQFactoryConfig {
	private static final String BROKER_URL = "vm://localhost";
	
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setTrustAllPackages(true);
		factory.setBrokerURL(BROKER_URL);
		return factory;
	}
	
	
}
