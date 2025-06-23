package com.sanvi.sanvi_api.controller.dto;

public class SpecialistDTO {
    private Long id;
    private String name;

    public SpecialistDTO() {}

    public SpecialistDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
