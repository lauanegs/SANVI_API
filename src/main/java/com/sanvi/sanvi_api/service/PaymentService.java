package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.domain.PaymentEntry;
import com.sanvi.sanvi_api.repository.PaymentEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentEntryRepository paymentEntryRepository;

    /**
     * Retorna todas as parcelas associadas a um tratamento.
     */
    public List<PaymentEntry> listAllByTreatment(Long treatmentId) {
        return paymentEntryRepository.findAllByTreatmentId(treatmentId);
    }

    /**
     * Retorna as parcelas de um tratamento ordenadas pela data de vencimento (mais próximas primeiro).
     */
    public List<PaymentEntry> listOrderedByDueDate(Long treatmentId) {
        return paymentEntryRepository.findAllByTreatmentIdOrderByDueDateAsc(treatmentId);
    }

    /**
     * Retorna as parcelas vencidas e ainda não pagas de um tratamento.
     */
    public List<PaymentEntry> listOverduePayments(Long treatmentId) {
    return paymentEntryRepository.findAllByTreatmentIdAndPaymentDateIsNullAndDueDateBefore(treatmentId, LocalDate.now());
}

    /**\
     * Retorna true se o tratamento possuir alguma parcela vencida e não paga.
     */
    public boolean hasOverduePayments(Long treatmentId) {
        return !listOverduePayments(treatmentId).isEmpty();
    }
}
