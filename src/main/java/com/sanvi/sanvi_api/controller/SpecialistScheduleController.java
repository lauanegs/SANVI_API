package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.domain.SpecialistSchedule;
import com.sanvi.sanvi_api.service.SpecialistScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/specialist-schedule")
public class SpecialistScheduleController {

    @Autowired
    private SpecialistScheduleService service;

    @GetMapping
    public List<SpecialistSchedule> list() {
        return service.listAll();
    }

    @GetMapping("/specialist/{id}")
    public List<SpecialistSchedule> listBySpecialist(@PathVariable("id") Long id) {
        return service.listBySpecialist(id);
    }

    @GetMapping("/{id}")
    public SpecialistSchedule findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/create")
    public SpecialistSchedule create(@RequestBody SpecialistSchedule schedule) {
        return service.create(schedule);
    }

    @PutMapping
    public SpecialistSchedule update(@RequestBody SpecialistSchedule schedule) {
        return service.update(schedule);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
