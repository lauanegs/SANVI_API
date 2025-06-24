package com.sanvi.sanvi_api.controller.dto;

import java.time.LocalDateTime;

import com.sanvi.sanvi_api.domain.MedicalRecordData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordPostDTO {
    private MedicalRecordDataDTO medicalRecordData;
    private Boolean isPregnant;

    private Boolean hasHealthProblem;

    private Boolean hasMedicalTreatment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
