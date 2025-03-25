package com.ecommerce.notification.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.OptimisticLockException;

public class VendorNotificationService {

    @KafkaListener(topics = "customer",groupId="vendor-processing-group")
    @Retryable(
        value = OptimisticLockException.class, 
        maxAttempts = 3, // Retry up to 3 times
        backoff = @Backoff(delay = 500) // Wait 500ms before retrying
    )
    @Transactional
    public void consume(ConsumerRecord<String,Object> record)  {
   
   
    }

    
}
