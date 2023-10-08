package com.example.demo.service;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerServiceImpl implements ProducerService {

	private final static Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

	JmsTemplate jmsTemplate;

	@Value("${application.activemq.queue1}")
	String queue1;

	@Value("${application.activemq.queue2}")
	String queue2;

	@Autowired
	ProducerServiceImpl(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void sendJmsMessages() {
		String content = "TODO";
		logger.info("envoi d'un message JMS: {}", content);

		jmsTemplate.send(queue1, messageCreator -> {
			TextMessage message = messageCreator.createTextMessage();
			message.setText(queue1 + " " + content);

			message.setJMSPriority(9);

			return message;
		});

		logger.info("envoi d'un message JMS: {}", content);
		jmsTemplate.send(queue2, messageCreator -> {
			TextMessage message = messageCreator.createTextMessage();
			message.setText(queue2 + " " + content);

			message.setJMSPriority(9);

			return message;
		});
	}

}
