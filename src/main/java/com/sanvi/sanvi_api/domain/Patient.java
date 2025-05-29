package com.sanvi.sanvi_api.domain;

import com.sanvi.sanvi_api.domain.enums.Gender;
import com.sanvi.sanvi_api.service.PatientService;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends Person{

    private String profession;

    @ManyToMany(mappedBy = "patients")
    private List<Treatment> treatments;

    public Patient(String name, String CPF, Date birthDate, Long phoneNumber, String address, int addressNumber, String neighborhood, Gender gender, String rg, String profession) {
        super(name, CPF, birthDate, phoneNumber, address, addressNumber, neighborhood, gender, rg);
        this.profession = profession;
    }
}
