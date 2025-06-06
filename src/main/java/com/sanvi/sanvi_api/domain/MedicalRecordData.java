package com.sanvi.sanvi_api.domain;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MedicalRecordData implements Serializable {
    private String mainComplaint;
    private String diseaseHistory;
    private String pastMedicalHistory;
    private String healthProblem;
    private String medicalTreatment;
    private String familyMedicalHistory;
}

