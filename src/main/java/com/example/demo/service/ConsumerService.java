package com.example.demo.service;

import javax.jms.Message;

public interface ConsumerService {

	void receiveMessage(Message message);
}
