# E-Commerce Services with RabbitMQ

This project demonstrates a simple example of asynchronous communication between **Payment** and **Order** services using RabbitMQ.

---

## Architecture

- **Payment Service:**  
  Simulates payment processing. If successful, sets order status to `PAID` and publishes a shipping message.  
  If failed, sets order status to `CANCELLED` and sends a cancellation message to the `order_cancelled_queue`.

- **Order Service:**  
  Listens to the `order_cancelled_queue`. Upon receiving a cancellation message, updates the corresponding order status to `CANCELLED` in the database.

---

## Technologies

- Java 21
- Spring Boot
- Spring AMQP (RabbitMQ)
- RabbitMQ
- Logback (Logging)

---

## RabbitMQ Configuration

### Order Service

- `order_exchange` (Topic Exchange)  
- `order_cancelled_queue` (Queue for order cancellation messages)  
- Routing key: `order.cancelled`

### Payment Service

- Publishes messages to `order_exchange` with routing key `order.cancelled`.

---

## How to Run

1. Start RabbitMQ server (default settings, localhost:5672).  
2. Run both services.  
3. Payment service processes payment messages and sends results as messages to the Order service.  
4. Order service updates order status based on cancellation messages.

---

## Example Message Publishing (Payment Service)

```java
rabbitTemplate.convertAndSend("order_exchange", "order.cancelled", orderDTO);
