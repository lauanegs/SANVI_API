package com.sanvi.sanvi_api.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.mapping.TypeDef;


@Entity
@Getter
@Setter
public class MedicalRecord extends BaseEntity{

    @Id
    private Long id;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private MedicalRecordData medicalRecordData;



}
