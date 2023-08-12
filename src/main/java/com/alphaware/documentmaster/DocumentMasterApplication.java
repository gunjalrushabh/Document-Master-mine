package com.alphaware.documentmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.annotations.OpenAPI30;

@SpringBootApplication
public class DocumentMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentMasterApplication.class, args);
	}

}
