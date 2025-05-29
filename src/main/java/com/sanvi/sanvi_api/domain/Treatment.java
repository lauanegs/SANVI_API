package com.sanvi.sanvi_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name="patient_treatment",
            joinColumns = @JoinColumn(name="treatment_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private List<Patient> patients;

    @OneToMany
    private List<PaymentEntry> paymentEntries;

    @OneToMany
    private List<JourneyEvent> events;

}
