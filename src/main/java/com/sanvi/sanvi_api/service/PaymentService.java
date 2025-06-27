package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.controller.dto.AlertaDTO;
import com.sanvi.sanvi_api.controller.dto.DashboardSummaryDTO;
import com.sanvi.sanvi_api.controller.dto.PaymentByMonthDTO;
import com.sanvi.sanvi_api.domain.PaymentEntry;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.repository.PaymentEntryRepository;
import com.sanvi.sanvi_api.repository.TreatmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<PaymentByMonthDTO> getMonthlyPaidValues() {
    LocalDate start = LocalDate.now().withDayOfYear(1); // 1º de janeiro
    LocalDate end = LocalDate.now().withMonth(12).withDayOfMonth(31); // 31 de dezembro

    List<PaymentEntry> entries = paymentEntryRepository.findByPaymentDateIsNotNullAndPaymentDateBetween(start, end);

    return entries.stream()
        .collect(Collectors.groupingBy(
            entry -> entry.getPaymentDate().format(DateTimeFormatter.ofPattern("MM/yyyy")),
            Collectors.mapping(PaymentEntry::getValue, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
        ))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey()) // garante ordem cronológica
        .map(entry -> new PaymentByMonthDTO(entry.getKey(), entry.getValue()))
        .toList();
    }

    // ----------

    public DashboardSummaryDTO getDashboardSummary() {
    List<PaymentEntry> entries = paymentEntryRepository.findByPaymentDateIsNotNull();

    LocalDate now = LocalDate.now();
    YearMonth currentMonth = YearMonth.of(now.getYear(), now.getMonth());
    YearMonth previousMonth = currentMonth.minusMonths(1);

    BigDecimal totalCaixa = BigDecimal.ZERO;
    BigDecimal recebidoAtual = BigDecimal.ZERO;
    BigDecimal recebidoAnterior = BigDecimal.ZERO;

    for (PaymentEntry entry : entries) {
        LocalDate date = entry.getPaymentDate();
        BigDecimal value = entry.getValue();

        if (value == null || date == null) continue;

        totalCaixa = totalCaixa.add(value);

        YearMonth entryMonth = YearMonth.from(date);
        if (entryMonth.equals(currentMonth)) {
            recebidoAtual = recebidoAtual.add(value);
        } else if (entryMonth.equals(previousMonth)) {
            recebidoAnterior = recebidoAnterior.add(value);
        }
    }

    return new DashboardSummaryDTO(totalCaixa, recebidoAtual, recebidoAnterior);
}



    
    public List<AlertaDTO> getMonthlyPendingAlerts() {
        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.withDayOfMonth(1);
        LocalDate lastDay = now.withDayOfMonth(now.lengthOfMonth());

        return paymentEntryRepository
            .findByPaymentDateIsNullAndDueDateBetween(firstDay, lastDay)
            .stream()
            .sorted(Comparator.comparing(PaymentEntry::getDueDate))
            .limit(2)
            .map(entry -> new AlertaDTO(
                entry.getId(),
                "Pagamento em atraso",
                entry.getTreatment().getPatient().getName()  // ajuste conforme seu modelo
            ))
            .toList();
    }

}
