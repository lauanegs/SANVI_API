package com.sanvi.sanvi_api.repository;

import com.sanvi.sanvi_api.domain.PaymentEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentEntryRepository extends JpaRepository<PaymentEntry, Long> {
    Optional<PaymentEntry> findByTreatmentId(Long treatment_id);
}