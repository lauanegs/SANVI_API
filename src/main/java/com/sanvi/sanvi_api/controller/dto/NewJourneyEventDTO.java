package com.sanvi.sanvi_api.controller.dto;

import java.time.LocalDate;

public class NewJourneyEventDTO {
    private String description;
    private LocalDate date;
    private Long specialistId;
    private Long treatmentId;

    public NewJourneyEventDTO(){}

    public NewJourneyEventDTO(String description, LocalDate date, Long specialistId, Long treatmentId){
        this.treatmentId = treatmentId;
        this.date = date;
        this.description = description;
        this.specialistId = specialistId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }
    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public LocalDate getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
    public Long getSpecialistId() {
        return specialistId;
    }
    public Long getTreatmentId() {
        return treatmentId;
    }
    
}
