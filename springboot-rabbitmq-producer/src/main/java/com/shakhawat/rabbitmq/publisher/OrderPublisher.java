package com.shakhawat.rabbitmq.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shakhawat.rabbitmq.config.MessagingConfig;
import com.shakhawat.rabbitmq.dto.Order;
import com.shakhawat.rabbitmq.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostMapping("/{resturent-name}")
	public String bookOrder(@RequestBody Order order, @PathVariable(name = "resturent-name") String resturantName) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderStatus orderStatus =  new OrderStatus(order, "In-progress", "The order has been placed successfully in "+resturantName);
		rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
		return "Success!";
	}

}
