package com.sanvi.sanvi_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanvi.sanvi_api.controller.dto.NewJourneyEventDTO;
import com.sanvi.sanvi_api.controller.dto.UpdateJourneyEventDTO;
import com.sanvi.sanvi_api.domain.JourneyEvent;
import com.sanvi.sanvi_api.service.JourneyEventService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/journey")
public class JourneyEventController {

    @Autowired
    private JourneyEventService journeyEventService;

    @GetMapping
    public List<JourneyEvent> list() {
        return journeyEventService.list();
    }

    @GetMapping("/{id}")
    public JourneyEvent findById(@PathVariable("id") Long id) {
        return journeyEventService.findById(id);
    }

    @GetMapping("/treatment/{id}")
    public List<JourneyEvent> listJourneyEventByTreatmentId(@PathVariable("id") Long id) {
        return journeyEventService.listJourneyEventByTreatmentId(id);
    }

    @PostMapping("/create")
    public JourneyEvent create(@RequestBody NewJourneyEventDTO journeyEventDTO) {
        return journeyEventService.create(journeyEventDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        journeyEventService.delete(id);
    }

    @PutMapping
    public JourneyEvent update(@RequestBody UpdateJourneyEventDTO journeyEvent) {
        return journeyEventService.update(journeyEvent);
    }
}
