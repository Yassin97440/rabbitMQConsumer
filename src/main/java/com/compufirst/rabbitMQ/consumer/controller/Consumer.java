package com.compufirst.rabbitMQ.consumer.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.compufirst.rabbitMQ.consumer.model.Message;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {

	
// sur le tuto le mec a mit queue.A mais en fte c'est routing.A psk coté producer on a mit routing et pas queues
	@RabbitListener(queues = "routing.A")
	private void receiveFromA(Message message) {
		System.out.println("Message received from queue A-> id: "+  message.getId() + "name : "+message.getName());
	}
	
	@RabbitListener(queues = "routing.B")
	private void receiveFromB(Message message) {
		System.out.println("Message received from queue B-> id: "+  message.getId() + "name : "+message.getName());
	}

}
