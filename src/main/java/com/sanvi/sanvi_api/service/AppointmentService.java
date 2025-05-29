package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.controller.dto.NewAppointment;
import com.sanvi.sanvi_api.domain.Appointment;
import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.domain.PaymentEntry;
import com.sanvi.sanvi_api.domain.Specialist;
import com.sanvi.sanvi_api.domain.enums.AppointmentStatus;
import com.sanvi.sanvi_api.domain.enums.PaymentStatus;
import com.sanvi.sanvi_api.repository.AppointmentRepository;
import com.sanvi.sanvi_api.repository.PatientRepository;
import com.sanvi.sanvi_api.repository.PaymentEntryRepository;
import com.sanvi.sanvi_api.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private PaymentEntryRepository paymentEntryRepository;
    private SpecialistRepository specialistRepository;

    public Appointment create(NewAppointment appointment){

//        Tratar caso 'Não vinculado a tratamento'
//        if(!appointment.isHasTreatment()){ }

//        Tratar caso 'Vinculado a tratamento'
//        if(appointment.isHasTreatment()){ }

        // Buscar objetos a partir dos IDs que chegam no request: OBSERVAR controller/dto/NewAppointment
        Patient patient = patientRepository.findById(appointment.getPatient_id()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado."));
        Specialist specialist = specialistRepository.findById(appointment.getSpecialist_id()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialista não encontrado."));
        Appointment appointment2 = new Appointment(patient,
                                        specialist,
                                        AppointmentStatus.Criado,
                                        appointment.isHasTreatment(),
                                        appointment.getDate(),
                                        appointment.getConfirmPhoneNumber() );

        return appointmentRepository.save(appointment2);
    }

    public List<Appointment> list(){
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id){
        return appointmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado."));
    }

    public void delete(Long id){
        appointmentRepository.deleteById(id);
    }

    //Fazer o update similar ao create
    public Appointment update(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

}
