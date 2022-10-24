package com.compufirst.rabbitMQ.consumer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

	private final String ROUTING_A = "routing.A";
	private final String ROUTING_B = "routing.B";
	@Bean
	Queue queueA() {
		//durable est à false, comprendre ce que c'est
		return new Queue(ROUTING_A, false);
	}

	@Bean
	Queue queueB() {
		//durable est à false, comprendre ce que c'est
		return new Queue(ROUTING_B, false);
	}
	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange("direct.exchange");
	}
	
	@Bean
	Binding binding(Queue queueA, DirectExchange exchange) {
		return BindingBuilder.bind(queueA)
				.to(exchange)
				.with(ROUTING_A);
	}
	
	@Bean
	Binding bindingB(Queue queueB, DirectExchange exchange) {
		return BindingBuilder.bind(queueB)
				.to(exchange)
				.with(ROUTING_B);
	}
	
	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	 
	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
		RabbitTemplate template = new RabbitTemplate(factory);
		template.setMessageConverter(messageConverter());
		return template;
	}
}
