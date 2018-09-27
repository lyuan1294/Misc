package com.ilpreprocessor.constants;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public enum ClientTestMessageEnums {
	NT ("NG"),
	GT ("GC"),
	ET ("ED"),
	MT ("MC");
	
	private String inputSource;
	
	private static Map<ClientTestMessageEnums, String> tagMap;
	
	static {
		Map<ClientTestMessageEnums, String> map = 
				new EnumMap<>(ClientTestMessageEnums.class);
		
		for(ClientTestMessageEnums tagEnum: ClientTestMessageEnums.values()) {
			map.put(tagEnum,  tagEnum.inputSource);
		}
		
		tagMap = Collections.unmodifiableMap(map);
	}
	
	ClientTestMessageEnums(String inputSource) {
		this.inputSource = inputSource;
	}
	
	
	public static Map<ClientTestMessageEnums, String> getTagEnumMap() {
		return tagMap;
	}
	
	public String getInputSource() {
		return inputSource;
	}
}
