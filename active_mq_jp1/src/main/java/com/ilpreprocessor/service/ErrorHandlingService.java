package com.ilpreprocessor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ilpreprocessor.exception.MessageException;

@Service
public class ErrorHandlingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlingService.class);
	
	public void execute(Exception e) {
		if (e instanceof MessageException) {
			LOGGER.error("Error occured in Message PRocessing", e.getCause());
		} else {
			LOGGER.warn("Connectiveity issue -- will attempt to rollback message");
		}
	}
}
