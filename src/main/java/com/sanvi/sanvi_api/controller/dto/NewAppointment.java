package com.sanvi.sanvi_api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.sanvi.sanvi_api.domain.enums.AppointmentStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewAppointment {

    private Long patientId;
    private Long specialistId;
    private LocalDateTime date;
    private String confirmPhoneNumber;
    private boolean hasTreatment;
    private Long treatmentId;
    private BigDecimal value;
    private AppointmentStatus status;


    

}
