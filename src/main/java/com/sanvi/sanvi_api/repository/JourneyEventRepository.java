package com.sanvi.sanvi_api.repository;

import org.springframework.stereotype.Repository;

import com.sanvi.sanvi_api.domain.JourneyEvent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface JourneyEventRepository extends JpaRepository<JourneyEvent, Long> {
    
    List<JourneyEvent> findAllByTreatmentIdOrderByDateAsc(Long treatmentId);

}
