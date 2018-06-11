package com.theam.CRMService.crmrestapi;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.theam.CRMService.crmrestapi.utils.FileStorageService;

@SpringBootApplication
public class CrmRestApiApplication implements CommandLineRunner {
	
	@Resource
	FileStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(CrmRestApiApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
