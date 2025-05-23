package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.controller.dto.NewAppointment;
import com.sanvi.sanvi_api.domain.Appointment;
import com.sanvi.sanvi_api.domain.PaymentEntry;
import com.sanvi.sanvi_api.domain.enums.PaymentStatus;
import com.sanvi.sanvi_api.repository.AppointmentRepository;
import com.sanvi.sanvi_api.repository.PatientRepository;
import com.sanvi.sanvi_api.repository.PaymentEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private PaymentEntryRepository paymentEntryRepository;

    public Optional<Appointment> create(NewAppointment appointment){

        // Checkbox 'Não' marcado -> O valor será escrito no momento, e a cobrança será 1 de 1.
        if(!appointment.isHasTreatment()){
            PaymentEntry entry = new PaymentEntry(
                    patientRepository.findById(appointment.getPatient_id()).orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado.")),
                    appointment.getValue(),
                    PaymentStatus.Pendente,
                    null,
                    1,
                    1
            );
           paymentEntryRepository.save(entry);
        }

        // Checkbox 'Sim' marcado -> O valor será escrito no momento, e a cobrança será incrementada de acordo com o tratamento
        if(appointment.isHasTreatment()){
            PaymentEntry paymentEntry = paymentEntryRepository.findByTreatmentId(appointment.getTreatmentId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado."));
        }




        return saved;
    }

}
