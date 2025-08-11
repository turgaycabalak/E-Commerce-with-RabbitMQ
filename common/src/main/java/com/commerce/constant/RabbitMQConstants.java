package com.commerce.constant;

public class RabbitMQConstants {
  public static final String ORDER_EXCHANGE = "order-exchange";

  public static final String PAYMENT_QUEUE = "payment-queue";
  public static final String ORDER_CANCELLED_QUEUE = "order-cancelled-queue";

  public static final String ROUTING_KEY_PAYMENT_CREATED = "order.created.payment";
  public static final String ROUTING_KEY_SHIPPING_CREATED = "order.created.shipping";

  public static final String ROUTING_KEY_PAYMENT_CANCELLED = "order.cancelled.payment";
  public static final String ROUTING_KEY_SHIPPING_CANCELLED = "order.cancelled.shipping";

}
