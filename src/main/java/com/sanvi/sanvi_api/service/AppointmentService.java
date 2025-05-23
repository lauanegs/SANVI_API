package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.domain.Appointment;
import com.sanvi.sanvi_api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment create(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> list(){
        return appointmentRepository.findAll();
    }

    public void delete(Long id){
        appointmentRepository.deleteById(id);
    }

    public Appointment update(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public Appointment findById(Long Id){
        return appointmentRepository.findById(Id).orElseThrow(() -> new RuntimeException("Appointment não encontrado."));
    }
}
