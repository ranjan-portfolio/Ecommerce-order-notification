package com.ecommerce.notification.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SendSMSService {

    @Value("${twilio.phone.number}")
    private String twilloPhoneNo;

    public String sendSMS(String message, String customerPhoneNo){
        Message sms= Message.creator(new PhoneNumber(customerPhoneNo),
                                         new PhoneNumber(twilloPhoneNo), 
                                         "customerPhoneNo").create();
        return "Sent message to recipient with SID:"+sms.getSid();
    }
    
}
