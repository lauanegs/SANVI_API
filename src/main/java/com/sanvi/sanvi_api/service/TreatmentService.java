package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.repository.PatientRepository;
import com.sanvi.sanvi_api.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Treatment> list() {
        return treatmentRepository.findAll();
    }

    public List<Treatment> listTreatmentsByPatientId(Patient patient) {
        return treatmentRepository.findAllByPatient(patient);
    }

    public Treatment findById(Long id) {
        return treatmentRepository.findById(id).orElse(null);
    }

    public Treatment create(Treatment treatment) {

        Long patientId = treatment.getPatient().getId();


        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o id: " + patientId));

        treatment.setPatient(patient);

        patient.getTreatments().add(treatment);

        return treatmentRepository.save(treatment);
    }

    public void delete(Long id) {
        treatmentRepository.deleteById(id);
    }

    public Treatment update(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

}
