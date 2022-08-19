package com.shakhawat.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.shakhawat.rabbitmq.config.MessagingConfig;
import com.shakhawat.rabbitmq.dto.OrderStatus;

@Component
public class BasicConsumer {

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumerMessageFromQueue(OrderStatus orderStatus) {
		System.out.println("Message received from queue: "+orderStatus);
	}
}
