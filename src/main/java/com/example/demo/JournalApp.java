package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApp {

	public static void main(String[] args) {

		SpringApplication.run(JournalApp.class, args);
	}

	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbstore){
		return new MongoTransactionManager(dbstore);
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
