package com.sanvi.sanvi_api.controller.dto;

import java.io.Serializable;

import com.sanvi.sanvi_api.domain.Patient;

public class PatientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long phoneNumber;

    // Construtor padrão
    public PatientDTO() {
    }

    // Construtor com parâmetros
    public PatientDTO(Long id, String name, Long phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public PatientDTO(Patient patient) {
    this.id = patient.getId();
    this.name = patient.getName();
    this.phoneNumber = patient.getPhoneNumber();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}