package com.sanvi.sanvi_api.controller.dto;

import com.sanvi.sanvi_api.domain.MedicalRecordData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalRecordDTO {
    private Long patientId;
    private MedicalRecordData data;
}
