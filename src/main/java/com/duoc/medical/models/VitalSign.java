package com.duoc.medical.models;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class VitalSign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VitalSignType type;
    private Double value;
    private String unit;
    private LocalDateTime measurementDate;
    private Boolean hist;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public VitalSignType getType() {
        return type;
    }
    public void setType(VitalSignType type) {
        this.type = type;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public LocalDateTime getMeasurementDate() {
        return measurementDate;
    }
    public void setMeasurementDate(LocalDateTime measurementDate) {
        this.measurementDate = measurementDate;
    }
   

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @JsonIgnore // Para evitar la exposición en la API
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @JsonIgnore
    public Boolean getHist() {
        return hist;
    }

    public void setHist(Boolean hist) {
        this.hist = hist;
    }

    
    @JsonIgnore
    public VitalSignLevel getVitalSignLevel() {
        if(type == VitalSignType.BodyTemperature){
            if(value < 97.8d){
                return VitalSignLevel.LOW;
            }
            else if(value > 99.1d) {
                return VitalSignLevel.HIGH;
            }
            else {
                return VitalSignLevel.NORMAL;
            }
        }
        else if (type == VitalSignType.RespiratoryRate) {
            if(value < 12){
                return VitalSignLevel.LOW;
            }
            else if(value > 18) {
                return VitalSignLevel.HIGH;
            }
            else {
                return VitalSignLevel.NORMAL;
            }
        }
        else if (type == VitalSignType.PulseRate) {
            if(value < 60){
                return VitalSignLevel.LOW;
            }
            else if(value > 100) {
                return VitalSignLevel.HIGH;
            }
            else {
                return VitalSignLevel.NORMAL;
            }
        }
        else if (type == VitalSignType.DiastolicBloodPressure) {
            if(value < 80){
                return VitalSignLevel.LOW;
            }
            else if(value > 120) {
                return VitalSignLevel.HIGH;
            }
            else {
                return VitalSignLevel.NORMAL;
            }
        }
        else if (type == VitalSignType.SystolicBloodPressure) {
            if(value < 60){
                return VitalSignLevel.LOW;
            }
            else if(value > 90) {
                return VitalSignLevel.HIGH;
            }
            else {
                return VitalSignLevel.NORMAL;
            }
        }
        return VitalSignLevel.NORMAL;
    }
}
