package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.domain.Specialist;
import com.sanvi.sanvi_api.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistService {

    @Autowired
    private SpecialistRepository SpecialistRepository;

    public Specialist create(Specialist Specialist){
        return SpecialistRepository.save(Specialist);
    }

    public List<Specialist> list(){
        return SpecialistRepository.findAll();
    }

    public void delete(Long id){
        SpecialistRepository.deleteById(id);
    }

    public Specialist update(Specialist Specialist){
        return SpecialistRepository.save(Specialist);
    }

    public Specialist findById(Long Id){
        return SpecialistRepository.findById(Id).orElseThrow(() -> new RuntimeException("Paciente não encontrado."));
    }
}
