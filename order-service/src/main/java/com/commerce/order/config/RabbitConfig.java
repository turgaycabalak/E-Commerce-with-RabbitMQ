package com.commerce.order.config;


import com.commerce.constant.RabbitMQConstants;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(messageConverter());
    template.setMandatory(true);
    return template;
  }

  @Bean
  public TopicExchange orderExchange() {
    return new TopicExchange(RabbitMQConstants.ORDER_EXCHANGE);
  }

  @Bean
  public Queue orderCancelledQueue() {
    return new Queue(RabbitMQConstants.ORDER_CANCELLED_QUEUE);
  }

  @Bean
  public Binding orderCancelledBinding(Queue orderCancelledQueue, TopicExchange orderExchange) {
    return BindingBuilder.bind(orderCancelledQueue)
        .to(orderExchange)
        .with(RabbitMQConstants.ORDER_CANCELLED_QUEUE);
  }
}
