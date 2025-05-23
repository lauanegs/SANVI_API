package com.sanvi.sanvi_api.controller;

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
    public Appointment findById(@PathVariable("id") Long Id) {
        return appointmentService.findById(Id);
    }

    @PostMapping("create")
    public Appointment create(@RequestBody Appointment appointment) {
        return appointmentService.create(appointment);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
    }

    @PutMapping("{id}")
    public Appointment update(@PathVariable("id") Long id, @RequestBody Appointment appointment) {
        appointment.setId(id); // Garante que o ID está correto
        return appointmentService.update(appointment);
    }

}
