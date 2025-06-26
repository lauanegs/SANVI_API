package com.sanvi.sanvi_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sanvi.sanvi_api.domain.enums.PaymentStatus;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate startedAt;

    @Column(nullable = true)
    private LocalDate endedAt;

    private BigDecimal totalValue;

    private BigDecimal amountPaid = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.Pendente;

    private Integer totalInstallments;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "treatments")
    private Patient patient;

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "paymentEntries")
    private List<PaymentEntry> paymentEntries = new ArrayList<>();

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "journeyEvents")
    private List<JourneyEvent> events = new ArrayList<>();

    public void updatePaymentStatus() {
        BigDecimal paid = paymentEntries.stream()
            .filter(p -> p.getPaymentDate() != null)
            .map(PaymentEntry::getValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.amountPaid = paid;

        if (paid.compareTo(totalValue) >= 0) {
            this.paymentStatus = PaymentStatus.Pago;
        } else if (paid.compareTo(BigDecimal.ZERO) == 0) {
            this.paymentStatus = PaymentStatus.Pendente;
        } else {
            this.paymentStatus = PaymentStatus.Parcial;
        } 
}
}