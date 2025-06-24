package com.sanvi.sanvi_api.controller.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentFullPostDTO {
    private String title;
    private LocalDate startedAt;
    private LocalDate endedAt;
    private Long patientId;
}
