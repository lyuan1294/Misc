package com.ilpreprocessor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ilpreprocessor.constants.ClientTestMessageEnums;
import com.ilpreprocessor.publisher.Publisher;

@Component
public class MessageFilterService {
	private static String tag72 = ":72:";
	private static String tag20 = ":20:";
	
	private final static Logger LOGGER = 
				LoggerFactory.getLogger(MessageFilterService.class);
	
	@Autowired
	Publisher publisher;
	
	public void tag72Filter(StringBuilder clientTestMessage) {
		int index72 = clientTestMessage.indexOf(tag72);
		
		if (index72 == -1) {
			getCCNID(clientTestMessage);
		}
		
		int indexNewLine = clientTestMessage.indexOf("\n", index72);
		
		if (indexNewLine != -1 && (clientTestMessage.length() > indexNewLine + 6)) {
			String clientIndicator = clientTestMessage.substring(indexNewLine + 4, indexNewLine + 6);
			
			if (isClientTestMessage(clientIndicator)) {
				clientTestMessage.replace(indexNewLine + 4, indexNewLine+6, ClientTestMessageEnums.getTagEnumMap().
							get(ClientTestMessageEnums.valueOf(clientIndicator)));
				LOGGER.info("Changed " + clientIndicator + " to " + ClientTestMessageEnums.getTagEnumMap().
						get(ClientTestMessageEnums.valueOf(clientIndicator)) + " in tag 72");
				System.out.println("Changed " + clientIndicator + " to " + ClientTestMessageEnums.getTagEnumMap().
						get(ClientTestMessageEnums.valueOf(clientIndicator)) + " in tag 72");

				publisher.sendMessage(clientTestMessage.toString());
			} else {
				LOGGER.info(getCCNID(clientTestMessage));
			}
		}
	}
	
	
	private String getCCNID(StringBuilder messageBuffer) {
		int index20 = messageBuffer.indexOf(tag20);
		int nextIndOne, nextIndTwo, nextInd = index20;
		
		String str = messageBuffer.substring(index20 + tag20.length());
		if (index20 != -1) {
			nextIndOne = str.indexOf(":");
			nextIndTwo = str.indexOf("-}");
			if (nextIndOne == -1 && nextIndTwo == -1) {
				nextInd = str.length();
			} else if (nextIndOne == -1) {
				nextInd = nextIndTwo;
			} else if (nextIndTwo == -1) {
				nextInd = nextIndOne;
			} else {
				nextInd = Math.min(nextIndOne,  nextIndTwo);
			}
		}
		str = str.replace("\n",  "");
		return str.substring(0, nextInd -1);
		
	}

	
	private boolean isClientTestMessage(String clientTestIndicator) {
		for (ClientTestMessageEnums clientTestMesssageEnum : ClientTestMessageEnums.values()) {
			if (clientTestMesssageEnum.name().equals(clientTestIndicator)) {
				return true;
			}
		}
		return false;
	}
			
	
	private String getClientTestID(String clientIndicator) {
		return ClientTestMessageEnums.getTagEnumMap().get(ClientTestMessageEnums.valueOf(clientIndicator));
	}
}
