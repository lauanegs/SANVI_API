package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.controller.dto.TreatmentFullDTO;
import com.sanvi.sanvi_api.controller.dto.TreatmentFullPostDTO;
import com.sanvi.sanvi_api.controller.dto.TreatmentDTO;
import com.sanvi.sanvi_api.domain.Treatment;
import com.sanvi.sanvi_api.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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



    @GetMapping("/dto")
    public List<TreatmentDTO> listDTO() {
        return treatmentService.list()
            .stream()
            .map(treatmentService::convertToDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/dto/{id}")
    public TreatmentDTO findByIdDTO(@PathVariable Long id) {
        Treatment treatment = treatmentService.findById(id);
        if (treatment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Treatment not found");
        }
        return treatmentService.convertToDTO(treatment);
    }


    @PostMapping("/fixed")
    public ResponseEntity<TreatmentDTO> createTreatment(@RequestBody TreatmentDTO dto) {
        Treatment treatment = treatmentService.saveWithPayments(dto);
        return ResponseEntity.ok(new TreatmentDTO(treatment));
    }
}