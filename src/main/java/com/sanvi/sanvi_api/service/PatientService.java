package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient create(Patient patient){
        return patientRepository.save(patient);
    }

    public List<Patient> list(){
        return patientRepository.findAll();
    }

    public void delete(Long id){
        patientRepository.deleteById(id);
    }

    public Patient update(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient findById(Long Id){
        return patientRepository.findById(Id).orElseThrow(() -> new RuntimeException("Paciente não encontrado."));
    }
}
