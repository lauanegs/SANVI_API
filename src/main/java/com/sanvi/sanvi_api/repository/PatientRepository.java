package com.sanvi.sanvi_api.repository;

import com.sanvi.sanvi_api.domain.Patient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByOrderByNameAsc();
}
