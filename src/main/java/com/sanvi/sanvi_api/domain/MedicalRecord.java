package com.sanvi.sanvi_api.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.hibernate.mapping.TypeDef;


@Entity
public class MedicalRecord extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private MedicalRecordData medicalRecordData;



}
