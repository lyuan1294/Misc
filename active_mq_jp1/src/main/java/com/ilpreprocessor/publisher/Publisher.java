package com.ilpreprocessor.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.ilpreprocessor.service.ErrorHandlingService;

@Component
public class Publisher {
	
	@Autowired
	ErrorHandlingService errorHandlingService;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	//@Value("TSSCTPPP.PYMTINIT.SWIFT.MHS.DC.LOCAL")
	@Value("${mq.outputQueue}")
	private String outputQueue;
	
	public void sendMessage(String message) {
		try {
			jmsTemplate.convertAndSend(outputQueue, message);
		} catch (Exception e) {
			errorHandlingService.execute(e);
		}
	}
	
	
}
