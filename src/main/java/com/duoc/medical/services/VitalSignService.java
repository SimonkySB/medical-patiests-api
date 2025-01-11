package com.duoc.medical.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.medical.exception.NotFoundException;
import com.duoc.medical.models.VitalSign;
import com.duoc.medical.repositories.VitalSignRepository;

@Service
public class VitalSignService {
    
    @Autowired
    private VitalSignRepository vitalSignRepository;


    public List<VitalSign> findAll(Long patientId) {
        if(patientId != null) {
            return vitalSignRepository.findByPatientId(patientId);
        }
        return vitalSignRepository.findAll();
    }


    public VitalSign add(VitalSign model) {
        if(model.getMeasurementDate() == null) {
            model.setMeasurementDate(LocalDateTime.now());
        }
        return vitalSignRepository.save(model);
    }


    public Optional<VitalSign> getById(Long id) {
        return vitalSignRepository.findById(id);
    }

    public VitalSign update(Long id, VitalSign model) {
        return vitalSignRepository.findById(id)
            .map(vitalSign -> {
                vitalSign.setType(model.getType());
                vitalSign.setValue(model.getValue());
                vitalSign.setUnit(model.getUnit());
                vitalSign.setMeasurementDate(model.getMeasurementDate());
                //vitalSign.setPatientId(model.getPatientId());
                
                return vitalSignRepository.save(vitalSign);
            })
            .orElseThrow(() -> new NotFoundException("Vital Sign not found for id " + id));
    }

    

    public void delete(Long id) {
        vitalSignRepository.deleteById(id);
    }
}
