package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.controller.dto.MedicalRecordDTO;
import com.sanvi.sanvi_api.controller.dto.PatientDTO;
import com.sanvi.sanvi_api.controller.dto.TreatmentDTO;
import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.service.PatientService;
import com.sanvi.sanvi_api.service.TreatmentService;

import com.sanvi.sanvi_api.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public List<Patient> list() {
        return patientService.list();
    }

    @GetMapping("/dto")
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> {
            PatientDTO dto = new PatientDTO();
            dto.setId(patient.getId());
            dto.setName(patient.getName());
            dto.setPhoneNumber(patient.getPhoneNumber());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}/treatment")
    public List<TreatmentDTO> listTreatments(@PathVariable Long id) {
        return treatmentService.listTreatmentsByPatientId(id);
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable("id") Long Id) {
        return patientService.findById(Id);
    }

    @PostMapping("/create")
    public Patient create(@RequestBody Patient patient) {
        return patientService.create(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        patientService.delete(id);
    }

    @PutMapping
    public Patient update(@RequestBody Patient patient) {
        return patientService.update(patient);
    }

    @PostMapping("/medical_record")
    public void setMedicalRecord(@RequestBody MedicalRecordDTO medicalRecord) {
        patientService.setMedicalRecord(medicalRecord);

    }
}
