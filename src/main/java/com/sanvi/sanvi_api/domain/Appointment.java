package com.sanvi.sanvi_api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sanvi.sanvi_api.domain.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonBackReference(value="appointments_patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "specialist_id", nullable = false)
    @JsonBackReference(value="appointments_specialist")
    private Specialist specialist;

    private AppointmentStatus status;

    private boolean hasTreatment;

    private LocalDateTime date;
    private String confirmPhoneNumber;

    public Appointment(Patient patient, Specialist specialist, AppointmentStatus status, boolean hasTreatment, LocalDateTime date, String confirmPhoneNumber) {
        this.patient = patient;
        this.specialist = specialist;
        this.status = status;
        this.hasTreatment = hasTreatment;
        this.date = date;
        this.confirmPhoneNumber = confirmPhoneNumber;
    }
}