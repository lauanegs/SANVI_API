package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.controller.dto.NewAppointment;
import com.sanvi.sanvi_api.domain.Appointment;
import com.sanvi.sanvi_api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> list() {
        return appointmentService.list();
    }

    @GetMapping("{id}")
    public Appointment findById(@PathVariable("id") Long id) {
        return appointmentService.findById(id);
    }

    @PostMapping("/create")
    // O request não vem com os objetos completos, mas com os IDs, por isso o DTO NewAppointment
    public Appointment create(@RequestBody NewAppointment newAppointment) {
        return appointmentService.create(newAppointment);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
    }

    @PutMapping("{id}")
    public Appointment update(@PathVariable("id") Long id, @RequestBody NewAppointment newAppointmentDto) {
        return appointmentService.update(id, newAppointmentDto);
    }
}
