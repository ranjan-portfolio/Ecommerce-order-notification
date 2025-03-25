package com.ecommerce.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

@Configuration
public class TwilloConfig {

    @Value("${twilio.account.sid}")
    private String twilloSid;
    @Value("${twilio.auth.token}")
    private String twilloToken;

    @Bean
    public void initTwillo(){
        Twilio.init(twilloToken, twilloSid);
    }
    
}
