package com.sanvi.sanvi_api.domain;

import com.sanvi.sanvi_api.domain.enums.Gender;
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

    @Column(length = 11)
    private String CPF;

    private Date birthDate;

    @Column(length = 15)
    private Long phoneNumber;

    private String address;
    private int addressNumber;
    private String neighborhood;

    private Gender gender;
    @Column(length = 10)
    private String rg;

}
