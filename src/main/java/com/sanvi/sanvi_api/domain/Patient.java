package com.sanvi.sanvi_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sanvi.sanvi_api.domain.enums.Gender;
import com.sanvi.sanvi_api.service.PatientService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Patient extends Person {

    private String profession;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "treatments")
    private List<Treatment> treatments;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    public Patient(String name, String CPF, Date birthDate, Long phoneNumber, String address, int addressNumber,
            String neighborhood, Gender gender, String rg, String profession) {
        super(name, CPF, birthDate, phoneNumber, address, addressNumber, neighborhood, gender, rg);
        this.profession = profession;
    }

}
