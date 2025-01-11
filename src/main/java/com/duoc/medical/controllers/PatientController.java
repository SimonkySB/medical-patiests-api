package com.duoc.medical.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.medical.models.Patient;
import com.duoc.medical.models.VitalSign;
import com.duoc.medical.services.PatientService;
import com.duoc.medical.services.VitalSignService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private VitalSignService vitalSignService;



    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        return ResponseEntity.ok(patientService.findAll());
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id) {
        return patientService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> add(@RequestBody Patient model) {
        return ResponseEntity.ok(patientService.add(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient model) {
        return ResponseEntity.ok(patientService.update(id, model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }


    // Vital Sign For Patient
    @GetMapping("/{id}/vital-signs")
    public ResponseEntity<List<VitalSign>> getVitalSigns(@PathVariable Long id) {
        var patient = patientService.getById(id);
        if(patient.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(vitalSignService.findAll(id));
    }

    @PostMapping("/{id}/vital-signs")
    public ResponseEntity<VitalSign> addVitalSign(@PathVariable Long id, @RequestBody VitalSign vitalSign) {
        var patient = patientService.getById(id);
        if(patient.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        vitalSign.setPatientId(id);
        return ResponseEntity.ok(vitalSignService.add(vitalSign));
    }
    
    
}
