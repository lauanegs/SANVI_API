package com.sanvi.sanvi_api.domain;

import com.sanvi.sanvi_api.domain.enums.SpecialistType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Specialist extends Person {

    @Enumerated(EnumType.STRING) 
    private SpecialistType specialistType;
}
