package com.sanvi.sanvi_api.repository;

import com.sanvi.sanvi_api.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {

}
