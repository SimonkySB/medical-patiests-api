package com.duoc.medical.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.medical.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
