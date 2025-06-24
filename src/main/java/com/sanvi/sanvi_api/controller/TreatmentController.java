package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.controller.dto.TreatmentFullDTO;
import com.sanvi.sanvi_api.controller.dto.TreatmentFullPostDTO;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/treatment")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @GetMapping
    public List<Treatment> list() {
        return treatmentService.list();
    }

    @GetMapping("/{id}")
    public Treatment findById(@PathVariable("id") Long id) {
        return treatmentService.findById(id);
    }

    @PostMapping("/create")
    public Treatment create(@RequestBody TreatmentFullPostDTO treatmentFullPostDTO) {
        return treatmentService.create(treatmentFullPostDTO);
    }

    @PutMapping
    public Treatment update(@RequestBody Treatment treatment) {
        return treatmentService.update(treatment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        treatmentService.delete(id);
    }
}