package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.controller.dto.MedicalRecordPostDTO;
import com.sanvi.sanvi_api.controller.dto.MedicalRecordPutDTO;
import com.sanvi.sanvi_api.controller.dto.PatientPostDTO;
import com.sanvi.sanvi_api.domain.MedicalRecord;
import com.sanvi.sanvi_api.domain.MedicalRecordData;
import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient create(PatientPostDTO patientPostDTO){
        Patient patient = new Patient();

        MedicalRecordData recordData = new MedicalRecordData();
        recordData.setDiseaseHistory(patientPostDTO.getMedicalRecord().getMedicalRecordData().getDiseaseHistory());
        recordData.setFamilyMedicalHistory(patientPostDTO.getMedicalRecord().getMedicalRecordData().getFamilyMedicalHistory());
        recordData.setHealthProblem(patientPostDTO.getMedicalRecord().getMedicalRecordData().getHealthProblem());
        recordData.setMainComplaint(patientPostDTO.getMedicalRecord().getMedicalRecordData().getMainComplaint());
        recordData.setMedicalTreatment(patientPostDTO.getMedicalRecord().getMedicalRecordData().getMedicalTreatment());
        recordData.setPastMedicalHistory(patientPostDTO.getMedicalRecord().getMedicalRecordData().getPastMedicalHistory());

        MedicalRecord record = new MedicalRecord();
        record.setCreatedAt(LocalDateTime.now());
        record.setHasHealthProblem(patientPostDTO.getMedicalRecord().getHasHealthProblem());
        record.setHasMedicalTreatment(patientPostDTO.getMedicalRecord().getHasMedicalTreatment());
        record.setIsPregnant(patientPostDTO.getMedicalRecord().getIsPregnant());
        record.setMedicalRecordData(recordData);
        record.setUpdatedAt(null);

        patient.setAddress(patientPostDTO.getAddress());
        patient.setAddressNumber(patientPostDTO.getAddressNumber());
        patient.setAppointments(null);
        patient.setBirthDate(patientPostDTO.getBirthDate());
        patient.setCEP(patientPostDTO.getCep());
        patient.setCPF(patientPostDTO.getCpf());
        patient.setPhoneNumber(patientPostDTO.getPhoneNumber());
        patient.setCreatedAt(LocalDateTime.now());
        patient.setGender(patientPostDTO.getGender());
        patient.setGuardianCPF(patientPostDTO.getGuardianCPF());
        patient.setGuardianName(patientPostDTO.getGuardianName());
        patient.setGuardianPhoneNumber(patientPostDTO.getGuardianPhoneNumber());
        patient.setName(patientPostDTO.getName());
        patient.setNeighborhood(patientPostDTO.getNeighborhood());
        patient.setProfession(patientPostDTO.getProfession());
        patient.setRg(patientPostDTO.getRg());
        patient.setTreatments(null);
        patient.setUf(patientPostDTO.getUf());
        patient.setUpdatedAt(null);

        patient.setMedicalRecord(record);
        record.setPatient(patient);

        return patientRepository.save(patient);
    }

    public List<Patient> list(){
        return patientRepository.findAllByOrderByNameAsc();
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

    public void editMedicalRecord(MedicalRecordPutDTO medicalRecordPutDto) {

        Patient patient = patientRepository.findById(medicalRecordPutDto.getPatientId())
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        patient.getMedicalRecord().getMedicalRecordData().setDiseaseHistory(medicalRecordPutDto.getMedicalRecordData().getDiseaseHistory());
        patient.getMedicalRecord().getMedicalRecordData().setFamilyMedicalHistory(medicalRecordPutDto.getMedicalRecordData().getFamilyMedicalHistory());
        patient.getMedicalRecord().getMedicalRecordData().setHealthProblem(medicalRecordPutDto.getMedicalRecordData().getHealthProblem());
        patient.getMedicalRecord().getMedicalRecordData().setMainComplaint(medicalRecordPutDto.getMedicalRecordData().getMainComplaint());
        patient.getMedicalRecord().getMedicalRecordData().setMedicalTreatment(medicalRecordPutDto.getMedicalRecordData().getMedicalTreatment());
        patient.getMedicalRecord().getMedicalRecordData().setPastMedicalHistory(medicalRecordPutDto.getMedicalRecordData().getPastMedicalHistory());

        patient.getMedicalRecord().setHasHealthProblem(medicalRecordPutDto.getHasHealthProblem());
        patient.getMedicalRecord().setHasMedicalTreatment(medicalRecordPutDto.getHasMedicalTreatment());
        patient.getMedicalRecord().setIsPregnant(medicalRecordPutDto.getIsPregnant());
        patient.getMedicalRecord().setUpdatedAt(medicalRecordPutDto.getUpdatedAt());
        
        patientRepository.save(patient);
    }
}