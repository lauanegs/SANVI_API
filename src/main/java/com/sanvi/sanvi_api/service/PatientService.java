package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.controller.dto.MedicalRecordPostDTO;
import com.sanvi.sanvi_api.controller.dto.MedicalRecordPutDTO;
import com.sanvi.sanvi_api.controller.dto.PatientPostDTO;
import com.sanvi.sanvi_api.controller.dto.PatientPutDTO;
import com.sanvi.sanvi_api.domain.MedicalRecord;
import com.sanvi.sanvi_api.domain.MedicalRecordData;
import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.repository.MedicalRecordRepository;
import com.sanvi.sanvi_api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

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

    public Patient update(PatientPutDTO patientPutDTO){
        Patient patient = patientRepository.findById(patientPutDTO.getId())
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        MedicalRecord medicalRecord = medicalRecordRepository.findById(patientPutDTO.getId())
            .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));

        patient.setAddress(patientPutDTO.getAddress());
        patient.setAddressNumber(patientPutDTO.getAddressNumber());
        patient.setBirthDate(patientPutDTO.getBirthDate());
        patient.setCEP(patientPutDTO.getCep());
        patient.setCPF(patientPutDTO.getCpf());
        patient.setPhoneNumber(patientPutDTO.getPhoneNumber());
        patient.setGender(patientPutDTO.getGender());
        patient.setGuardianCPF(patientPutDTO.getGuardianCPF());
        patient.setGuardianName(patientPutDTO.getGuardianName());
        patient.setGuardianPhoneNumber(patientPutDTO.getGuardianPhoneNumber());
        patient.setName(patientPutDTO.getName());
        patient.setUpdatedAt(patientPutDTO.getUpdatedAt());
        patient.setNeighborhood(patientPutDTO.getNeighborhood());
        patient.setProfession(patientPutDTO.getProfession());
        patient.setRg(patientPutDTO.getRg());
        patient.setUf(patientPutDTO.getUf());

        MedicalRecordData newMedicalRecordData = medicalRecord.getMedicalRecordData();
        newMedicalRecordData.setDiseaseHistory(patientPutDTO.getMedicalRecord().getMedicalRecordData().getDiseaseHistory());
        newMedicalRecordData.setFamilyMedicalHistory(patientPutDTO.getMedicalRecord().getMedicalRecordData().getFamilyMedicalHistory());
        newMedicalRecordData.setHealthProblem(patientPutDTO.getMedicalRecord().getMedicalRecordData().getHealthProblem());
        newMedicalRecordData.setMainComplaint(patientPutDTO.getMedicalRecord().getMedicalRecordData().getMainComplaint());
        newMedicalRecordData.setMedicalTreatment(patientPutDTO.getMedicalRecord().getMedicalRecordData().getMedicalTreatment());
        newMedicalRecordData.setPastMedicalHistory(patientPutDTO.getMedicalRecord().getMedicalRecordData().getPastMedicalHistory());

        medicalRecord.setMedicalRecordData(newMedicalRecordData);
        medicalRecord.setHasHealthProblem(patientPutDTO.getMedicalRecord().getHasHealthProblem());
        medicalRecord.setHasMedicalTreatment(patientPutDTO.getMedicalRecord().getHasMedicalTreatment());
        medicalRecord.setIsPregnant(patientPutDTO.getMedicalRecord().getIsPregnant());
        medicalRecord.setUpdatedAt(patientPutDTO.getMedicalRecord().getUpdatedAt());

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