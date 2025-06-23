package com.sanvi.sanvi_api.controller.dto;

import java.time.LocalDate;

public class UpdateJourneyEventDTO {
    private Long id;
    private String description;
    private LocalDate date;
    private Long specialistId;
    private Long treatmentId;

    public UpdateJourneyEventDTO(){}

    public UpdateJourneyEventDTO(String description, LocalDate date, Long specialistId, Long treatmentId){
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
    public void setId(Long id) {
        this.id = id;
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
    public Long getId() {
        return id;
    }
}
