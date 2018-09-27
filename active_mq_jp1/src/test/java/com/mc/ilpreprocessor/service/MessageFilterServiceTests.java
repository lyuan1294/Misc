package com.mc.ilpreprocessor.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ilpreprocessor.publisher.Publisher;
import com.ilpreprocessor.service.MessageFilterService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MessageFilterServiceTests {

	@InjectMocks
	private MessageFilterService messageFilterService;
	
	@Mock
	private Publisher publisher;
	
	private String ETMESSAGE = "{1:DATCAAP     XXX0000015608}{2:o1031200160912MHSDATxxxxxx000003603912091612000}"+
			":20:625MEX000110000\n"+
			":72:JCBINH2H0000000001625522040000000000\n" +
			"TLKET          N           N\n"+
			"-       \n"+
			"-}";
	
	private String EDMESSAGE = "{1:DATCAAP     XXX0000015608}{2:o1031200160912MHSDATxxxxxx000003603912091612000}"+
			":20:625MEX000110000\n"+
			":72:JCBINH2H0000000001625522040000000000\n" +
			"TLXED          N           N\n"+
			"-       \n"+
			"-}";
	
	
	@Test
	public void clientTestMessageSentToFilterShouldBeSentToPublisher() {
		StringBuilder messageBuffer = new StringBuilder(ETMESSAGE);
		messageFilterService.tag72Filter(messageBuffer);
		Mockito.verify(publisher, Mockito.times(1)).sendMessage(messageBuffer.toString());
	}
	
	@Test
	public void nonClientTestMessageSentToFilterShouldBeDropped() {
		StringBuilder messageBuffer = new StringBuilder(EDMESSAGE);
		messageFilterService.tag72Filter(messageBuffer);
		Mockito.verify(publisher, Mockito.times(0)).sendMessage(messageBuffer.toString());
	}
	
}
