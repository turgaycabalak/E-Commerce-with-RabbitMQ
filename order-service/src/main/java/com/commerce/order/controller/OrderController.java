package com.commerce.order.controller;

import java.util.UUID;

import com.commerce.constant.RabbitMQConstants;
import com.commerce.dto.OrderDTO;
import com.commerce.dto.StatusEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
  private final RabbitTemplate rabbitTemplate;

  @PostMapping
  public String createOrder(@RequestBody OrderDTO orderCreateRequest) {
    orderCreateRequest.setId(UUID.randomUUID());
    orderCreateRequest.setStatus(StatusEnum.CREATED);
    // Take order and save it into db
    log.info("Order created: {}", orderCreateRequest);

    // Publish the Payment message
    orderCreateRequest.setStatus(StatusEnum.ON_PAYMENT);
    rabbitTemplate.convertAndSend(
        RabbitMQConstants.ORDER_EXCHANGE,
        RabbitMQConstants.ROUTING_KEY_PAYMENT_CREATED,
        orderCreateRequest
    );
    log.info("Order sent to payment service.");

    return "Order created and payment messages sent: " + orderCreateRequest;
  }
}
