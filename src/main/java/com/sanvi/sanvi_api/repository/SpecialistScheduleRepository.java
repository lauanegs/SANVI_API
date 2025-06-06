package com.sanvi.sanvi_api.repository;

import com.sanvi.sanvi_api.domain.SpecialistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialistScheduleRepository extends JpaRepository<SpecialistSchedule, Long> {
    List<SpecialistSchedule> findBySpecialistId(Long specialistId);
}
