package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.domain.Specialist;
import com.sanvi.sanvi_api.service.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/specialist")
public class SpecialistController {

    @Autowired
    private SpecialistService SpecialistService;

    @GetMapping
    public List<Specialist> list(){
        return SpecialistService.list();
    }

    @GetMapping("{id}")
    public Specialist findById(@PathVariable("id") Long Id){
        return SpecialistService.findById(Id);
    }

    @PostMapping("create")
    public Specialist create(@RequestBody Specialist Specialist){
         System.out.println("Recebido: " + Specialist);
        return SpecialistService.create(Specialist);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        SpecialistService.delete(id);
    }

    @PutMapping
    public Specialist update(@RequestBody Specialist Specialist){
        return SpecialistService.update(Specialist);
    }

}
