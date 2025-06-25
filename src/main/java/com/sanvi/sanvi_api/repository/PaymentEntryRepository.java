package com.sanvi.sanvi_api.repository;

import com.sanvi.sanvi_api.domain.PaymentEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PaymentEntryRepository extends JpaRepository<PaymentEntry, Long> {

    List<PaymentEntry> findAllByTreatmentId(Long treatmentId);

    List<PaymentEntry> findAllByTreatmentIdOrderByDueDateAsc(Long treatmentId);

// 🔧 CORRETO:
    List<PaymentEntry> findAllByTreatmentIdAndPaymentDateIsNullAndDueDateBefore(Long treatmentId, LocalDate date);
}

