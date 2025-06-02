package com.sanvi.sanvi_api.domain;

import com.sanvi.sanvi_api.domain.enums.Gender;
import com.sanvi.sanvi_api.domain.enums.UF;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 150)
    private String name;

    @Column(length = 150)
    private String guardianName;

    @Column(length = 11)
    private String CPF;

    @Column(length = 11)
    private String guardianCPF;

    private Date birthDate;

    @Column(length = 15)
    private Long phoneNumber;

    @Column(length = 15)
    private Long guardianPhoneNumber;

    private String address;

    @Column(length = 8)
    private String CEP;

    @Column
    private int addressNumber;

    private String neighborhood;

    @Column(length = 2)
    @Enumerated(EnumType.STRING)
    private UF uf;

    private Gender gender;
    @Column(length = 10)
    private String rg;


    public Person(String name, String CPF, Date birthDate, Long phoneNumber, String address, int addressNumber, String neighborhood, Gender gender, String rg) {
        this.name = name;
        this.CPF = CPF;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.addressNumber = addressNumber;
        this.neighborhood = neighborhood;
        this.gender = gender;
        this.rg = rg;
    }
}
