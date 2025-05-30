package com.sanvi.sanvi_api.repository;

import com.sanvi.sanvi_api.domain.PaymentEntry;
import com.sanvi.sanvi_api.domain.Treatment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
