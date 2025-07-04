package com.sanvi.sanvi_api.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordData implements Serializable {
    private String mainComplaint;
    private String diseaseHistory;
    private String pastMedicalHistory;
    private String healthProblem;
    private String medicalTreatment;
    private String familyMedicalHistory;
}

