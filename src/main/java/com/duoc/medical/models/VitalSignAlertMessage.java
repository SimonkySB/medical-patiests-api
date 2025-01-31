package com.duoc.medical.models;

public class VitalSignAlertMessage {
    private Long vitalSignId;
    private Long patientId;
    private String patientName;
    private String field;
    private Double value;
    private String unit;
    private String level;
    
    public Long getPatientId() {
        return patientId;
    }
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
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
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public Long getVitalSignId() {
        return vitalSignId;
    }
    public void setVitalSignId(Long vitalSignId) {
        this.vitalSignId = vitalSignId;
    }
    
    


}
