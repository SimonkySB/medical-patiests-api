package com.duoc.medical.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducerService {
    @Autowired 
    private RabbitTemplate rabbitTemplate;

    public void sendAlert(String message)
    {
        rabbitTemplate.convertAndSend(
            "exchange-rabbit", "routing-key-alert", message);
    }


    public void sendSummary(String message)
    {
        rabbitTemplate.convertAndSend(
            "exchange-rabbit", "routing-key-summary", message);
    }
}
