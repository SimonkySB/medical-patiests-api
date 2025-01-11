package com.duoc.medical.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.medical.models.VitalSign;

public interface VitalSignRepository extends JpaRepository<VitalSign, Long> {
    List<VitalSign> findByPatientId(Long patientId);
}
