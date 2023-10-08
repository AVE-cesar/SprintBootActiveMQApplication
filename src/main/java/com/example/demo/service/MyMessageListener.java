package com.example.demo.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMessageListener implements MessageListener {

	private final static Logger logger = LoggerFactory.getLogger(MyMessageListener.class);

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				logger.info("message lu depuis le MessageListener: {}", ((TextMessage) message).getText());
			} catch (JMSException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}

}
