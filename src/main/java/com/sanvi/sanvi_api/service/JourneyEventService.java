package com.sanvi.sanvi_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanvi.sanvi_api.controller.dto.NewJourneyEventDTO;
import com.sanvi.sanvi_api.controller.dto.UpdateJourneyEventDTO;
import com.sanvi.sanvi_api.domain.JourneyEvent;
import com.sanvi.sanvi_api.domain.Specialist;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.repository.JourneyEventRepository;
import com.sanvi.sanvi_api.repository.SpecialistRepository;
import com.sanvi.sanvi_api.repository.TreatmentRepository;

@Service
public class JourneyEventService {
    @Autowired
    private JourneyEventRepository journeyEventRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private SpecialistRepository specialistRepository;

    public List<JourneyEvent> list() {
        return journeyEventRepository.findAll();
    }

    public List<JourneyEvent> listJourneyEventByTreatmentId(long treatmentId) {
        return journeyEventRepository.findAllByTreatmentIdOrderByDateAsc(treatmentId);
    }

    public JourneyEvent findById(Long id) {
        return journeyEventRepository.findById(id).orElse(null);
    }

    public JourneyEvent create(NewJourneyEventDTO journeyEventDTO) {

        System.out.println("Recebido DTO: TreatmentId=" + journeyEventDTO.getTreatmentId() +
                ", SpecialistId=" + journeyEventDTO.getSpecialistId());

        Treatment treatment = treatmentRepository.findById(journeyEventDTO.getTreatmentId())
                .orElseThrow(() -> new RuntimeException(
                        "Tratamento não encontrado com o id: " + journeyEventDTO.getTreatmentId()));

        Specialist specialist = specialistRepository.findById(journeyEventDTO.getSpecialistId())
                .orElseThrow(() -> new RuntimeException(
                        "Especialista não encontrado com o id: " + journeyEventDTO.getSpecialistId()));

        JourneyEvent newJourneyEvent = new JourneyEvent();
        newJourneyEvent.setDate(journeyEventDTO.getDate());
        newJourneyEvent.setDescription(journeyEventDTO.getDescription());
        newJourneyEvent.setSpecialist(specialist);
        newJourneyEvent.setTreatment(treatment);

        return journeyEventRepository.save(newJourneyEvent);
    }

    public void delete(Long id) {
        journeyEventRepository.deleteById(id);
    }

    public JourneyEvent update(UpdateJourneyEventDTO journeyEventDTO) {

        JourneyEvent journeyEventRep = journeyEventRepository.findById(journeyEventDTO.getId())
                .orElseThrow(() -> new RuntimeException(
                        "Especialista não encontrado com o id: " + journeyEventDTO.getId()));
        
        Treatment treatment = treatmentRepository.findById(journeyEventDTO.getTreatmentId())
                .orElseThrow(() -> new RuntimeException(
                        "Tratamento não encontrado com o id: " + journeyEventDTO.getTreatmentId()));

        Specialist specialist = specialistRepository.findById(journeyEventDTO.getSpecialistId())
                .orElseThrow(() -> new RuntimeException(
                        "Especialista não encontrado com o id: " + journeyEventDTO.getSpecialistId()));                
            
        journeyEventRep.setDate(journeyEventDTO.getDate());
        journeyEventRep.setDescription(journeyEventDTO.getDescription());
        journeyEventRep.setSpecialist(specialist);
        journeyEventRep.setTreatment(treatment);

        return journeyEventRepository.save(journeyEventRep);
    }
}
