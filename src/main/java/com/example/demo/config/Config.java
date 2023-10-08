package com.example.demo.config;

import javax.jms.MessageListener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.demo.service.MyMessageListener;

@Configuration
public class Config {

	@Value("${spring.activemq.broker-url}")
	String BROKER_URL;
	@Value("${spring.activemq.user}")
	String BROKER_USERNAME;
	@Value("${spring.activemq.password}")
	String BROKER_PASSWORD;

	@Value("${application.activemq.queue2}")
	String queue2;

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setTrustAllPackages(true);
		connectionFactory.setBrokerURL(BROKER_URL);
		connectionFactory.setPassword(BROKER_USERNAME);
		connectionFactory.setUserName(BROKER_PASSWORD);
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());

		template.setPubSubDomain(false);

		template.setDeliveryPersistent(true);

		return template;
	}

	// @Bean
	TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setMaxPoolSize(100);
		taskExecutor.setQueueCapacity(1000);
		taskExecutor.setThreadGroupName("MyJMSThreads_-_");

		return taskExecutor;
	}

	@Bean
	public MessageListener MyMessageListener() {
		return new MyMessageListener();
	}

	@Bean
	public MessageListenerContainer myMessageListenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setDestinationName(queue2); // Set the queue name here.
		container.setMessageListener(MyMessageListener()); // Your JMS receiver message listener.
		// container.setConcurrency("10-100");
		// container.setMaxMessagesPerTask(1);

		// container.setTaskExecutor(taskExecutor());

		return container;
	}
}
