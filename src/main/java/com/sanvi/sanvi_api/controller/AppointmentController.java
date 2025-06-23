package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.controller.dto.UpdateAppointment;
import com.sanvi.sanvi_api.controller.dto.AppointmentDTO;
import com.sanvi.sanvi_api.controller.dto.NewAppointment;
import com.sanvi.sanvi_api.controller.dto.PatientDTO;
import com.sanvi.sanvi_api.controller.dto.SpecialistDTO;
import com.sanvi.sanvi_api.domain.Appointment;
import com.sanvi.sanvi_api.service.AppointmentService;
import com.sanvi.sanvi_api.repository.*;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public List<Appointment> list() {
        return appointmentService.list();
    }

    @GetMapping("/dto")
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(appointment -> {
            AppointmentDTO dto = new AppointmentDTO();
            dto.setId(appointment.getId());
            dto.setStatus(appointment.getStatus());
            dto.setHasTreatment(appointment.isHasTreatment());
            dto.setDate(appointment.getDate());
            dto.setConfirmPhoneNumber(appointment.getConfirmPhoneNumber());

            // Mapeando Patient -> PatientDTO
            PatientDTO patientDTO = new PatientDTO();
            patientDTO.setId(appointment.getPatient().getId());
            patientDTO.setName(appointment.getPatient().getName());
            patientDTO.setPhoneNumber(appointment.getPatient().getPhoneNumber());
            dto.setPatient(patientDTO);

            // Mapeando Specialist -> SpecialistDTO
            SpecialistDTO specialistDTO = new SpecialistDTO();
            specialistDTO.setId(appointment.getSpecialist().getId());
            specialistDTO.setName(appointment.getSpecialist().getName());
            dto.setSpecialist(specialistDTO);

            return dto;
        }).toList();
    }

    @GetMapping("{id}")
    public Appointment findById(@PathVariable("id") Long id) {
        return appointmentService.findById(id);
    }

    @PostMapping("/create")
    // O request não vem com os objetos completos, mas com os IDs, por isso o DTO
    // NewAppointment
    public Appointment create(@RequestBody NewAppointment newAppointment) {
        return appointmentService.create(newAppointment);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
    }

    @PutMapping("{id}")
    public Appointment update(@PathVariable("id") Long id, @RequestBody UpdateAppointment updateAppointmentDto) {
        return appointmentService.update(id, updateAppointmentDto);
    }
}
