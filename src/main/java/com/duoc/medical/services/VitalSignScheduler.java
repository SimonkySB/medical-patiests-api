package com.duoc.medical.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.duoc.medical.models.VitalSign;
import com.duoc.medical.models.VitalSignSchedulerMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VitalSignScheduler {

    @Autowired
    private VitalSignService vitalSignService;
    @Autowired
    private RabbitProducerService rabbitService;
    


    @Scheduled(fixedRate = 1000 * 60 * 1) // 5 min
    public void sendVitalSignsToQueue() {
        var vitalSigns = vitalSignService.findVitalSignsWithHistNullOrFalse();

        List<VitalSignSchedulerMessage> data = new ArrayList<VitalSignSchedulerMessage>();

        for (VitalSign vitalSign : vitalSigns) {
            var patient = vitalSign.getPatient();

            var item = new VitalSignSchedulerMessage();
            item.setPatientId(patient.getId());
            item.setPatientName(patient.getFirstName() + ' ' + patient.getLastName());
            item.setField(vitalSign.getType().toString());
            item.setValue(vitalSign.getValue());
            item.setUnit(vitalSign.getUnit());
            item.setVitalSignId(vitalSign.getId());
            data.add(item);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(data);

            rabbitService.sendSummary(jsonMessage);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
    }
}

