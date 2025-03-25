package com.ecommerce.notification.dto;

import java.security.Timestamp;
import java.util.List;

import com.ecommerce.notification.dto.OrderStatus;
import com.ecommerce.notification.dto.PaymentStatus;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Order {
  
    private Long orderId;
    private String username;
    private Float totalAmount;
    private String currency;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private String shippingAddress;
    private Timestamp createdAt;
    private Timestamp updatedAt;
  
    private List<OrderItem> orderItemList;
    

}
