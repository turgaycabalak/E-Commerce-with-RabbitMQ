package com.commerce.payment.listener;

import com.commerce.constant.RabbitMQConstants;
import com.commerce.dto.OrderDTO;
import com.commerce.dto.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentListener {
  private final RabbitTemplate rabbitTemplate;

  @RabbitListener(queues = RabbitMQConstants.PAYMENT_QUEUE)
  public void handlePayment(OrderDTO orderDTO) {
    // Payment processes ...
    // Simulate payment success with 50% probability
    boolean paymentSuccess = Math.random() < 0.5;

    if (paymentSuccess) {
      orderDTO.setStatus(StatusEnum.PAID);
      log.info("Payment successful for the order: {}", orderDTO);

      // Publish the Shipping message
    } else {
      orderDTO.setStatus(StatusEnum.CANCELLED);
      log.warn("Payment failed for the order: {}", orderDTO);

      // Publish order failed message
      rabbitTemplate.convertAndSend(
          RabbitMQConstants.ORDER_EXCHANGE,
          RabbitMQConstants.ORDER_CANCELLED_QUEUE,
          orderDTO
      );
    }
  }
}
