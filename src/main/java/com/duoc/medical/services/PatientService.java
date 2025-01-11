package com.duoc.medical.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.medical.exception.NotFoundException;
import com.duoc.medical.models.Patient;
import com.duoc.medical.repositories.PatientRepository;


@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }


    public Patient add(Patient model) {
        return patientRepository.save(model);
    }


    public Optional<Patient> getById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient update(Long id, Patient model) {
        return patientRepository.findById(id)
            .map(patient -> {
                patient.setFirstName(model.getFirstName());
                patient.setLastName(model.getLastName());
                patient.setAge(model.getAge());
                patient.setAdmissionDate(model.getAdmissionDate());
                patient.setBedNumber(model.getBedNumber());
                patient.setDiagnosis(model.getDiagnosis());
                patient.setStatus(model.getStatus());
                return patientRepository.save(patient);
            })
            .orElseThrow(() -> new NotFoundException("Patient not found for id " + id));
    }

    

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
