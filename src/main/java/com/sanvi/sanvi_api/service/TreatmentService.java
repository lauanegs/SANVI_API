package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    public List<Treatment> list() {
        return treatmentRepository.findAll();
    }

    public List<Treatment> listTreatmentsByPatientId(Patient patient) {
        return treatmentRepository.findAllByPatient(patient);
    }

    public Treatment findById(Long id) {
        Optional<Treatment> treatment = treatmentRepository.findById(id);
        return treatment.orElseThrow(() -> new RuntimeException("Tratamento não encontrado com id: " + id));
    }

    public Treatment create(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    public Treatment update(Treatment treatment) {
        if (treatment.getId() == null || !treatmentRepository.existsById(treatment.getId())) {
            throw new RuntimeException("Não é possível atualizar: tratamento inexistente.");
        }
        return treatmentRepository.save(treatment);
    }

    public void delete(Long id) {
        if (!treatmentRepository.existsById(id)) {
            throw new RuntimeException("Tratamento não encontrado com id: " + id);
        }
        treatmentRepository.deleteById(id);
    }


}
