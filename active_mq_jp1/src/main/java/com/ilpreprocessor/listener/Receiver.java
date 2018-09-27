package com.ilpreprocessor.listener;

import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.ilpreprocessor.publisher.Publisher;
import com.ilpreprocessor.service.MessageFilterService;

@Component
public class Receiver {

	@Autowired
	MessageFilterService messageFilterService;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	////@Autowired
	////Publisher publisher;

    @JmsListener(destination ="${mq.inputQueue}", containerFactory="jmsFactory")
    public void receiveMessage(String message) {
    	
    	StringBuilder messageBuffer = new StringBuilder(message);
    	
    	LOGGER.info("Message Received from inputQueue");
        System.out.println("Received Message: " + message);
        
        messageFilterService.tag72Filter(messageBuffer);
		////publisher.sendMessage(message + "$$$$$");
				
		
		    
    }
    
       
    @JmsListener(destination = "${mq.outputQueue}")
    public void listener2(String message) {
        System.out.println("Received Message from outputQueue: " + message);
    }
}
