package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.controller.dto.TreatmentDTO;
import com.sanvi.sanvi_api.controller.dto.TreatmentFullDTO;
import com.sanvi.sanvi_api.controller.dto.TreatmentFullPostDTO;
import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Treatment> list() {
        return treatmentRepository.findAll();
    }

    public List<TreatmentDTO> listTreatmentsDTOByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        List<Treatment> treatments = treatmentRepository.findAllByPatient(patient);

        return treatments.stream()
                .map(t -> new TreatmentDTO(t.getId(), t.getTitle()))
                .collect(Collectors.toList());
    }

    public List<TreatmentFullDTO> listTreatmentsByPatientId(Long patientId) {
        
        var treatments = treatmentRepository.findAllByPatientId(patientId);

        List<TreatmentFullDTO> treatmentFullDTOs = new ArrayList<TreatmentFullDTO>();

        if(treatments.size() > 0){
            treatments.forEach(treatment -> {
                var treatmentDto = new TreatmentFullDTO();
                treatmentDto.setEndedAt(treatment.getEndedAt());
                treatmentDto.setId(treatment.getId());
                treatmentDto.setPatientId(patientId);
                treatmentDto.setTitle(treatment.getTitle());
                treatmentDto.setStartedAt(treatment.getStartedAt());
                
                treatmentFullDTOs.add(treatmentDto);
            });
        }

        return treatmentFullDTOs;
    }

    public Treatment findById(Long id) {
        Optional<Treatment> treatment = treatmentRepository.findById(id);
        return treatment.orElseThrow(() -> new RuntimeException("Tratamento não encontrado com id: " + id));
    }

    public Treatment create(TreatmentFullPostDTO treatmentFullPostDTO) {
        Patient patient = patientRepository.findById(treatmentFullPostDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com o id: " + treatmentFullPostDTO.getPatientId()));

        Treatment treatment = new Treatment();

        treatment.setPatient(patient);
        treatment.setEndedAt(null);
        treatment.setStartedAt(treatmentFullPostDTO.getStartedAt());
        treatment.setTitle(treatmentFullPostDTO.getTitle());
        treatment.setEvents(null);
        treatment.setPaymentEntries(null);

        return treatmentRepository.save(treatment);
    }

    public Treatment update(Treatment treatment) {
        if (treatment.getId() == null || !treatmentRepository.existsById(treatment.getId())) {
            throw new RuntimeException("Não é possível atualizar: tratamento inexistente.");
        }
        return treatmentRepository.save(treatment);
    }

    public void delete(Long id) {
        if (!treatmentRepository.existsById(id)) {
            throw new RuntimeException("Tratamento não encontrado com id: " + id);
        }
        treatmentRepository.deleteById(id);
    }

}