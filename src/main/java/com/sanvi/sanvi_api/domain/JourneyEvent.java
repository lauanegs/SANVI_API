package com.sanvi.sanvi_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JourneyEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    @JsonIgnoreProperties({
    "createdAt",
    "updatedAt",
    "guardianName",
    "guardianCPF",
    "birthDate",
    "phoneNumber",
    "guardianPhoneNumber",
    "address",
    "addressNumber",
    "neighborhood",
    "uf",
    "gender",
    "rg",
    "specialistType",
    "cep",
    "cpf",
    "journeyEvents"
    })
    private Specialist specialist;
    
    @ManyToOne
    @JoinColumn(name = "treatment_id")
    @JsonBackReference(value = "journeyEvents")
    private Treatment treatment;

}
