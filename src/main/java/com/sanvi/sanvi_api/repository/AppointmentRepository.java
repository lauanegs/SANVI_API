package com.sanvi.sanvi_api.repository;

import com.sanvi.sanvi_api.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
