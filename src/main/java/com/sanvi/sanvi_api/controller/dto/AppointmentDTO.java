package com.sanvi.sanvi_api.controller.dto;

import com.sanvi.sanvi_api.domain.enums.AppointmentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AppointmentDTO {

    private Long id;
    private PatientDTO patient;
    private SpecialistDTO specialist;
    private LocalDateTime date;
    private String confirmPhoneNumber;
    private boolean hasTreatment;
    private Long treatmentId;
    private BigDecimal value;
    private AppointmentStatus status;

    public AppointmentDTO() {}

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PatientDTO getPatient() { return patient; }
    public void setPatient(PatientDTO patient) { this.patient = patient; }

    public SpecialistDTO getSpecialist() { return specialist; }
    public void setSpecialist(SpecialistDTO specialist) { this.specialist = specialist; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getConfirmPhoneNumber() { return confirmPhoneNumber; }
    public void setConfirmPhoneNumber(String confirmPhoneNumber) { this.confirmPhoneNumber = confirmPhoneNumber; }

    public boolean isHasTreatment() { return hasTreatment; }
    public void setHasTreatment(boolean hasTreatment) { this.hasTreatment = hasTreatment; }

    public Long getTreatmentId() { return treatmentId; }
    public void setTreatmentId(Long treatmentId) { this.treatmentId = treatmentId; }

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }
}