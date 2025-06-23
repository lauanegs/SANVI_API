package com.sanvi.sanvi_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "treatments")
    private Patient patient;

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "paymentEntries")
    private List<PaymentEntry> paymentEntries = new ArrayList<PaymentEntry>();

    @OneToMany(mappedBy = "treatment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "journeyEvents")
    private List<JourneyEvent> events = new ArrayList<JourneyEvent>();

}
