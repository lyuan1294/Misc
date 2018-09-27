package com.ilpreprocessor.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@EnableJms
@Configuration
public class Config {

	@Value("${mq.inputQueue}")
	String inputQueue;
	
	@Value("${mq.outputQueue}")
	String outputQueue;
	
	
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(inputQueue);
    }
    
    
    @Bean
    public Queue queue2() {
        return new ActiveMQQueue(outputQueue);
    }
}
