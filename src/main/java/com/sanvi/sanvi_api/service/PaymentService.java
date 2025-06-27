package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.domain.PaymentEntry;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.repository.PaymentEntryRepository;
import com.sanvi.sanvi_api.repository.TreatmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentEntryRepository paymentEntryRepository;
    
    @Autowired
    private TreatmentRepository treatmentRepository;

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


    public PaymentEntry payInstallment(Long id, LocalDate paymentDate) {
        PaymentEntry entry = paymentEntryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Parcela não encontrada"));

        // Define a data de pagamento
        entry.setPaymentDate(paymentDate);
        PaymentEntry savedEntry = paymentEntryRepository.save(entry);

        // Atualiza o tratamento
        Treatment treatment = entry.getTreatment();
        treatment.updatePaymentStatus(); // atualiza amountPaid e paymentStatus
        treatmentRepository.save(treatment); // salva as alterações

        return savedEntry;
    }

}
