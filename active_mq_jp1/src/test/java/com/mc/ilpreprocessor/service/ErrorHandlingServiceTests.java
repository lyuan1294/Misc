package com.mc.ilpreprocessor.service;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.ilpreprocessor.exception.MessageException;
import com.ilpreprocessor.service.ErrorHandlingService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ErrorHandlingServiceTests {
	
	@InjectMocks
	private ErrorHandlingService errorHandlingService;
	
	@Mock
	private Exception exception;
	
	@Mock
	private MessageException messageException;
	
	@Test
	public void messageExceptinoTest() {
		errorHandlingService.execute(messageException);
	}
	
	@Test
	public void nonMessageExceptionTest() {
		errorHandlingService.execute(exception);
	}
}