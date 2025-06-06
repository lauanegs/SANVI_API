package com.sanvi.sanvi_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.mapping.TypeDef;


@Entity
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MedicalRecord extends BaseEntity{

    @Id
    private Long id;

    @OneToOne(mappedBy = "medicalRecord")
    @JsonIgnore
    private Patient patient;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private MedicalRecordData medicalRecordData;

    private Boolean isPregnant;

    private Boolean hasHealthProblem;

    private Boolean hasMedicalTreatment;

}
