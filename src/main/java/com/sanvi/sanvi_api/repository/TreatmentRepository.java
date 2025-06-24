package com.sanvi.sanvi_api.repository;

import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.domain.PaymentEntry;
import com.sanvi.sanvi_api.domain.Treatment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    
    List<Treatment> findAllByPatient(Patient patient);

    List<Treatment> findAllByPatientId(Long patientId);
}
