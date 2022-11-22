package com.compufirst.rabbitMQ.consumer.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.compufirst.rabbitMQ.consumer.db.model.User;
import com.compufirst.rabbitMQ.consumer.db.repository.UserRepository;
import com.compufirst.rabbitMQ.consumer.model.Message;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {

	@Autowired
	UserRepository userRepository;
	
// sur le tuto le mec a mit queue.A mais en fte c'est routing.A psk coté producer on a mit routing et pas queues
	@RabbitListener(queues = "routing.A")
	private void receiveFromA(Message message) {
		System.out.println("Message received from queue A-> id: "+  message.getId() + "name : "+message.getName());
		User user = new User();
		user.setName(message.getName());
		user.setEmail(message.getEmail());
		user = userRepository.save(user);
	}
	
	@RabbitListener(queues = "routing.B")
	private void receiveFromB(Message message) {
		System.out.println("Message received from queue B-> id: "+  message.getId() + "name : "+message.getName());
	}

}
