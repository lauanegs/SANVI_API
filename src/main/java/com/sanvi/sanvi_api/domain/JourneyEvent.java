package com.sanvi.sanvi_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;

@Entity
public class JourneyEvent {

    private LocalDate date;

    private String description;

    @OneToMany
    private Specialist specialist;

    @ManyToOne
    private Treatment treatment;
    //Imagem?

}
