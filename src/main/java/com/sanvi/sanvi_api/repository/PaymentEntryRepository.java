package com.sanvi.sanvi_api.repository;

import com.sanvi.sanvi_api.controller.dto.PaymentByMonthDTO;
import com.sanvi.sanvi_api.domain.PaymentEntry;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaymentEntryRepository extends JpaRepository<PaymentEntry, Long> {

    List<PaymentEntry> findAllByTreatmentId(Long treatmentId);

    List<PaymentEntry> findAllByTreatmentIdOrderByDueDateAsc(Long treatmentId);

// 🔧 CORRETO:
    List<PaymentEntry> findAllByTreatmentIdAndPaymentDateIsNullAndDueDateBefore(Long treatmentId, LocalDate date);

    List<PaymentEntry> findByPaymentDateIsNotNullAndPaymentDateBetween(LocalDate start, LocalDate end);

    List<PaymentEntry> findByPaymentDateIsNotNull();

    List<PaymentEntry> findByPaymentDateIsNullAndDueDateBetween(LocalDate start, LocalDate end);
}

