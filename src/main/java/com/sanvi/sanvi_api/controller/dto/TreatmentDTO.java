

package com.sanvi.sanvi_api.controller.dto;

import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import java.util.stream.Collectors;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.domain.PaymentEntry;

public class TreatmentDTO {

    private Long id;
    private String title;
    private LocalDate startedAt;
    private LocalDate endedAt;

    private BigDecimal totalValue;
    private BigDecimal amountPaid;
    private PaymentStatus paymentStatus;
    private Integer totalInstallments;

    private List<PaymentEntryDTO> paymentEntries;
    private PatientDTO patient;

    private boolean overdue; // ✅ novo campo

    public TreatmentDTO() {}

    public TreatmentDTO(Long id, String title, LocalDate startedAt, LocalDate endedAt,
                        BigDecimal totalValue, BigDecimal amountPaid, PaymentStatus paymentStatus,
                        Integer totalInstallments, List<PaymentEntryDTO> paymentEntries,
                        PatientDTO patient, boolean overdue) {
        this.id = id;
        this.title = title;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.totalValue = totalValue;
        this.amountPaid = amountPaid;
        this.paymentStatus = paymentStatus;
        this.totalInstallments = totalInstallments;
        this.paymentEntries = paymentEntries;
        this.patient = patient;
        this.overdue = overdue; // ✅ novo campo no construtor
    }

    public TreatmentDTO(Treatment treatment) {
    this.id = treatment.getId();
    this.title = treatment.getTitle();
    this.startedAt = treatment.getStartedAt();
    this.endedAt = treatment.getEndedAt();
    this.totalValue = treatment.getTotalValue();
    this.amountPaid = treatment.getAmountPaid();
    this.paymentStatus = treatment.getPaymentStatus();
    this.totalInstallments = treatment.getTotalInstallments();
    this.patient = new PatientDTO(treatment.getPatient());

    this.paymentEntries = treatment.getPaymentEntries()
        .stream()
        .map(PaymentEntryDTO::new)
        .collect(Collectors.toList());

    this.overdue = this.paymentEntries.stream()
        .anyMatch(p -> p.getPaymentDate() == null && p.getDueDate().isBefore(LocalDate.now()));
}

    // Getters e setters

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

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getTotalInstallments() {
        return totalInstallments;
    }

    public void setTotalInstallments(Integer totalInstallments) {
        this.totalInstallments = totalInstallments;
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

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
}
