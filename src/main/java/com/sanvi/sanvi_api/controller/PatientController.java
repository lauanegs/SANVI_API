package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.domain.Patient;
import com.sanvi.sanvi_api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> list(){
        return patientService.list();
    }

    @GetMapping("{id}")
    public Patient findById(@PathVariable("id") Long Id){
        return patientService.findById(Id);
    }

    @PostMapping("create")
    public Patient create(@RequestBody Patient patient){
        return patientService.create(patient);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        patientService.delete(id);
    }

    @PutMapping
    public Patient update(@RequestBody Patient patient){
        return patientService.update(patient);
    }

}
