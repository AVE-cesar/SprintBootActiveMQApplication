package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.example.demo.service.ProducerService;

@SpringBootApplication
@EnableJms
public class SpringBootActiveMQApp implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(SpringBootActiveMQApp.class);

	@Autowired
	ApplicationContext context;

	ProducerService producerService;

	@Autowired
	SpringBootActiveMQApp(ProducerService aProducerService) {
		producerService = aProducerService;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Let's play !!!");

		producerService.sendJmsMessages();

		DefaultMessageListenerContainer container = context.getBean(DefaultMessageListenerContainer.class);

		logger.info("On attend {} secondes", 10);
		Thread.sleep(1000 * 10);
		logger.info("On stoppe le container");
		container.shutdown();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActiveMQApp.class, args);
	}
}
