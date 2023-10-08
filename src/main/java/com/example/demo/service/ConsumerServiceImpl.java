package com.example.demo.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsumerServiceImpl implements ConsumerService {

	private final static Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

	@Override
	// @JmsListener(destination = "${application.activemq.queue1}")
	public void receiveMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			String content;
			try {
				content = textMessage.getText();
				logger.info("message read: {}", content);
			} catch (JMSException e) {
				logger.warn("", e);
			}

		}
	}

}
