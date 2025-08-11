package com.commerce.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderDTO {
  private UUID id;
  private String orderId;
  private String userId;
  private BigDecimal amount;
  private StatusEnum status;

  public OrderDTO(String orderId, String userId, BigDecimal amount) {
    this.orderId = orderId;
    this.userId = userId;
    this.amount = amount;
  }

  public OrderDTO() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "OrderDTO{" +
        "id=" + id +
        ", orderId='" + orderId + '\'' +
        ", userId='" + userId + '\'' +
        ", amount=" + amount +
        ", status=" + status +
        '}';
  }
}
