package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.ProducerService;

@SpringBootApplication
public class SpringBootActiveMQApp implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(SpringBootActiveMQApp.class);

	ProducerService producerService;

	@Autowired
	SpringBootActiveMQApp(ProducerService aProducerService) {
		producerService = aProducerService;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Let's play !!!");

		producerService.sendJmsMessages();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActiveMQApp.class, args);
	}
}
