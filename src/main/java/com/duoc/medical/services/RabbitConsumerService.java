package com.duoc.medical.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.medical.models.VitalSignSchedulerMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitConsumerService {

    @Autowired VitalSignService vitalSignService;
    

    @RabbitListener(queues = "summary-res")
    public void receiveAlert(String message) {

        try {
            System.out.println("Summary Res: " + message);

            ObjectMapper objectMapper = new ObjectMapper();
            List<VitalSignSchedulerMessage> vitalSigns = objectMapper.readValue(
                message,
                new TypeReference<List<VitalSignSchedulerMessage>>() {}
            );
            List<Long> ids = vitalSigns.stream().map(v -> v.getVitalSignId()).collect(Collectors.toList());

            vitalSignService.updateHistToTrue(ids);
            
        } catch(Exception ex) {
            System.out.println("Summary Res: " + ex.getMessage());
        }
        
    }
}
