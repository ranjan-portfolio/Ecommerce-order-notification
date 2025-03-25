package com.ecommerce.notification.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.notification.dto.Order;

import jakarta.persistence.OptimisticLockException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerNotificationService {

    @Autowired
    private SendSMSService sendSMSService;

    @Autowired
    private EmailSendService emailSendService;

    @KafkaListener(topics = "customer",groupId="vendor-processing-group")
    @Retryable(
        value = OptimisticLockException.class, 
        maxAttempts = 3, // Retry up to 3 times
        backoff = @Backoff(delay = 500) // Wait 500ms before retrying
    )
    @Transactional
    public void consume(ConsumerRecord<String,Object> record)  {
        Order order = (Order) record.value();
        String username= order.getUsername();
        //Call webservice to get customer email,phoneno details
        String phoneno="+447459537679"; //This will be customer phone no
        String email="ranjanabha@gmail.com"; //This will be customer email no
        String message="Hello"+username+"Your order has been notified to vendor:";

        sendSMSService.sendSMS(message, phoneno);
        String deliveryStatus=emailSendService.sendEmail(email, "", "Your order has been notified to vendor", "Your order has been notified to vendor");
   
    }

    
}
