package com.sanvi.sanvi_api.domain;


import com.sanvi.sanvi_api.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntry
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private BigDecimal value;

    @Enumerated(EnumType.STRING) 
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name="treatment_id", nullable = true)
    private Treatment treatment;

    private int billingPaid;

    private int billingLeft;

    public PaymentEntry(Patient patient, BigDecimal value, PaymentStatus status, Treatment treatment, int billingPaid, int billingLeft) {
        this.patient = patient;
        this.value = value;
        this.status = status;
        this.treatment = treatment;
        this.billingPaid = billingPaid;
        this.billingLeft = billingLeft;
    }
}
