package com.sanvi.sanvi_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Person{

    private String profession;

    @ManyToMany(mappedBy = "patients")
    private List<Treatment> treatments;

}
