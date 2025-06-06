package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.controller.dto.MedicalRecordDTO;
import com.sanvi.sanvi_api.domain.MedicalRecord;
import com.sanvi.sanvi_api.domain.MedicalRecordData;
import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.repository.MedicalRecordRepository;
import com.sanvi.sanvi_api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public Patient create(Patient patient){
        return patientRepository.save(patient);
    }

    public List<Patient> list(){
        return patientRepository.findAll();
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }

    public Patient update(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient findById(Long Id){
        return patientRepository.findById(Id).orElseThrow(() -> new RuntimeException("Paciente não encontrado."));
    }

    public void setMedicalRecord(MedicalRecordDTO dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        MedicalRecord record = patient.getMedicalRecord();
        if (record == null) {
            record = new MedicalRecord();

            record.setId(patient.getId());

            record.setPatient(patient);
            patient.setMedicalRecord(record);
        }

        record.setMedicalRecordData(dto.getData());
        record.setIsPregnant(dto.getIsPregnant());
        record.setHasHealthProblem(dto.getHasHealthProblem());
        record.setHasMedicalTreatment(dto.getHasMedicalTreatment());

        patientRepository.save(patient);
    }
}
