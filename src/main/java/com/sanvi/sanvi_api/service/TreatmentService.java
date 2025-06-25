package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.repository.TreatmentRepository;
import com.sanvi.sanvi_api.controller.dto.TreatmentDTO;
import com.sanvi.sanvi_api.controller.dto.PatientDTO;
import com.sanvi.sanvi_api.controller.dto.PaymentEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanvi.sanvi_api.service.PaymentService;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PaymentService paymentService; // ✅ Novo: injetar o PaymentService

    public List<Treatment> list() {
        return treatmentRepository.findAll();
    }

    public List<Treatment> listTreatmentsByPatientId(Patient patient) {
        return treatmentRepository.findAllByPatient(patient);
    }

    public Treatment findById(Long id) {
        return treatmentRepository.findById(id).orElse(null);
    }

    public Treatment create(Treatment treatment) {
        treatment.updatePaymentStatus(); // calcula status com base nas parcelas
        return treatmentRepository.save(treatment);
    }

    public void delete(Long id) {
        treatmentRepository.deleteById(id);
    }

    public Treatment update(Treatment treatment) {
        treatment.updatePaymentStatus(); // garante consistência ao atualizar
        return treatmentRepository.save(treatment);
    }

    public PatientDTO convertToPatientDTO(Patient patient) {
        if (patient == null) return null;

        return new PatientDTO(
            patient.getId(),
            patient.getName(),
            patient.getCPF(),
            patient.getBirthDate(),
            patient.getPhoneNumber(),
            patient.getAddress(),
            patient.getAddressNumber(),
            patient.getNeighborhood(),
            patient.getGender(),
            patient.getRg(),
            patient.getProfession()
        );
    }

    public TreatmentDTO convertToDTO(Treatment treatment) {
        List<PaymentEntryDTO> paymentEntryDTOS = treatment.getPaymentEntries()
            .stream()
            .map(pe -> new PaymentEntryDTO(
                pe.getId(),
                pe.getValue(),
                pe.getInstallmentNumber(),
                pe.getDueDate(),
                pe.getPaymentDate()
            ))
            .collect(Collectors.toList());

        PatientDTO patientDTO = convertToPatientDTO(treatment.getPatient());

        boolean overdue = paymentService.hasOverduePayments(treatment.getId()); // ✅ Verifica atraso

        return new TreatmentDTO(
            treatment.getId(),
            treatment.getTitle(),
            treatment.getStartedAt(),
            treatment.getEndedAt(),
            treatment.getTotalValue(),
            treatment.getAmountPaid(),
            treatment.getPaymentStatus(),
            treatment.getTotalInstallments(),
            paymentEntryDTOS,
            patientDTO,
            overdue // ✅ Envia no DTO
        );
    }
}
