package com.sanvi.sanvi_api.controller.dto;

import java.time.LocalDate;
import java.util.List;

public class TreatmentDTO {

    private Long id;
    private String title;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private List<PaymentEntryDTO> paymentEntries;
    private PatientDTO patient;

    public TreatmentDTO() {}

    public TreatmentDTO(Long id, String title, LocalDate startedAt, LocalDate endedAt,
                        List<PaymentEntryDTO> paymentEntries, PatientDTO patient) {
        this.id = id;
        this.title = title;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.paymentEntries = paymentEntries;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDate startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDate getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDate endedAt) {
        this.endedAt = endedAt;
    }

    public List<PaymentEntryDTO> getPaymentEntries() {
        return paymentEntries;
    }

    public void setPaymentEntries(List<PaymentEntryDTO> paymentEntries) {
        this.paymentEntries = paymentEntries;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }
}
