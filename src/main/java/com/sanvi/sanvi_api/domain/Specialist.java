package com.sanvi.sanvi_api.domain;

import com.sanvi.sanvi_api.domain.enums.SpecialistType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Specialist extends Person {

    private SpecialistType specialistType;

}

