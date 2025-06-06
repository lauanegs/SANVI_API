package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.domain.SpecialistSchedule;
import com.sanvi.sanvi_api.repository.SpecialistScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistScheduleService {

    @Autowired
    private SpecialistScheduleRepository repository;

    public List<SpecialistSchedule> listAll() {
        return repository.findAll();
    }

    public List<SpecialistSchedule> listBySpecialist(Long specialistId) {
        return repository.findBySpecialistId(specialistId);
    }

    public SpecialistSchedule findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public SpecialistSchedule create(SpecialistSchedule schedule) {
        return repository.save(schedule);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public SpecialistSchedule update(SpecialistSchedule schedule) {
        return repository.save(schedule);
    }
}
