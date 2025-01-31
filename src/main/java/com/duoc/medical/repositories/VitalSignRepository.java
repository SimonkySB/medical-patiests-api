package com.duoc.medical.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.duoc.medical.models.VitalSign;

public interface VitalSignRepository extends JpaRepository<VitalSign, Long> {
    List<VitalSign> findByPatientId(Long patientId);

    @Query("SELECT v FROM VitalSign v WHERE v.hist IS NULL OR v.hist = FALSE")
    List<VitalSign> findVitalSignsWithHistNullOrFalse();

    @Modifying
    @Transactional
    @Query("UPDATE VitalSign v SET v.hist = TRUE WHERE v.id IN :ids")
    void updateHistToTrue(@Param("ids") List<Long> ids);
}
