package com.sanvi.sanvi_api.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sanvi.sanvi_api.domain.PaymentEntry;

public class PaymentEntryDTO {

    private Long id;
    private BigDecimal value;
    private Integer installmentNumber;
    private LocalDate dueDate;
    private LocalDate paymentDate;

    public PaymentEntryDTO() {}

    public PaymentEntryDTO(Long id, BigDecimal value, Integer installmentNumber,
                           LocalDate dueDate, LocalDate paymentDate) {
        this.id = id;
        this.value = value;
        this.installmentNumber = installmentNumber;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        
    }

    public PaymentEntryDTO(PaymentEntry entry) {
    this.id = entry.getId();
    this.value = entry.getValue();
    this.installmentNumber = entry.getInstallmentNumber();
    this.dueDate = entry.getDueDate();
    this.paymentDate = entry.getPaymentDate();
}

    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Integer getInstallmentNumber() {
        return installmentNumber;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setInstallmentNumber(Integer installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
