package com.commerce.order.listener;

import com.commerce.constant.RabbitMQConstants;
import com.commerce.dto.OrderDTO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OrderCancelledListener {

  @RabbitListener(queues = RabbitMQConstants.ORDER_CANCELLED_QUEUE)
  public void handlePayment(OrderDTO orderDTO) {
    // Update status as CANCELLED
    log.info("Order status CANCELLED: {}", orderDTO);
  }
}
