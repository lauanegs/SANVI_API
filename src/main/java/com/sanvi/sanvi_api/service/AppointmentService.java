package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.controller.dto.UpdateAppointment;
import com.sanvi.sanvi_api.controller.dto.NewAppointment;
import com.sanvi.sanvi_api.domain.Appointment;
import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.domain.PaymentEntry;
import com.sanvi.sanvi_api.domain.Specialist;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.repository.AppointmentRepository;
import com.sanvi.sanvi_api.repository.PatientRepository;
import com.sanvi.sanvi_api.repository.PaymentEntryRepository;
import com.sanvi.sanvi_api.repository.TreatmentRepository;
import com.sanvi.sanvi_api.repository.SpecialistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PaymentEntryRepository paymentEntryRepository;

    @Autowired
    private SpecialistRepository specialistRepository;

    public Appointment create(NewAppointment appointment) {

        Patient patient = patientRepository.findById(appointment.getPatientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Paciente não encontrado."));
        Specialist specialist = specialistRepository.findById(appointment.getSpecialistId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Especialista não encontrado."));

        BigDecimal value = appointment.getValue();

        if (value != null && value.compareTo(BigDecimal.ZERO) > 0) {
            if (appointment.isHasTreatment()) {
                Treatment treatment = treatmentRepository.findById(appointment.getTreatmentId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Tratamento não encontrado."));

                PaymentEntry paymentEntry = new PaymentEntry();
                paymentEntry.setValue(value);
                paymentEntry.setInstallmentNumber(1);
                paymentEntry.setDueDate(appointment.getDate().toLocalDate());
                paymentEntry.setPaymentDate(null); // ainda não pago
                paymentEntry.setTreatment(treatment);

                treatment.getPaymentEntries().add(paymentEntry);
                treatment.updatePaymentStatus();
                treatmentRepository.save(treatment);
            } else {
                // Caso queira registrar um pagamento sem tratamento, aqui você pode implementar
                // a lógica de criar um "tratamento avulso" ou apenas ignorar
            }
        }

        Appointment appointmentEntity = new Appointment(
                patient,
                specialist,
                appointment.getStatus(),
                appointment.isHasTreatment(),
                appointment.getDate(),
                appointment.getConfirmPhoneNumber()
        );

        return appointmentRepository.save(appointmentEntity);
    }

    public List<Appointment> list() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Agendamento não encontrado."));
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    public Appointment update(Long id, UpdateAppointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Agendamento não encontrado."));

        Patient patient = patientRepository.findById(appointment.getPatientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Paciente não encontrado."));
        Specialist specialist = specialistRepository.findById(appointment.getSpecialistId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Especialista não encontrado."));

        existingAppointment.setPatient(patient);
        existingAppointment.setSpecialist(specialist);
        existingAppointment.setStatus(appointment.getStatus());
        existingAppointment.setDate(appointment.getDate());
        existingAppointment.setConfirmPhoneNumber(appointment.getConfirmPhoneNumber());

        return appointmentRepository.save(existingAppointment);
    }
}
