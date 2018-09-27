package com.ilpreprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class IlPreprocessorApplication {

	/* after app starts
	 * http://localhost:8081/rest/publish/HeyYou
	 */
	public static void main(String[] args) {
		SpringApplication.run(IlPreprocessorApplication.class, args);
	}
}
