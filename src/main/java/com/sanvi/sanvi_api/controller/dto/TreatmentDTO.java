package com.sanvi.sanvi_api.controller.dto;

public class TreatmentDTO {
    private Long id;
    private String title;

    public TreatmentDTO() {}

    public TreatmentDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}