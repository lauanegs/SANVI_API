package com.sanvi.sanvi_api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;

    private Integer installmentNumber; // Número da parcela (ex: 1, 2, 3...)

    private LocalDate dueDate;
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "treatment_id", nullable = true)
    @JsonBackReference(value = "paymentEntries")
    private Treatment treatment;

    public PaymentEntry(BigDecimal value, Integer installmentNumber,
                        LocalDate dueDate, LocalDate paymentDate, Treatment treatment) {
        this.value = value;
        this.installmentNumber = installmentNumber;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.treatment = treatment;
    }
}
