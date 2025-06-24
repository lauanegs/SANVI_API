package com.sanvi.sanvi_api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDataDTO {
    private String diseaseHistory;
    private String mainComplaint;
    private String medicalTreatment;
    private String pastMedicalHistory;
    private String healthProblem;
    private String familyMedicalHistory;
;
}
