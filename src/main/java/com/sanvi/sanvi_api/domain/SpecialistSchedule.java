package com.sanvi.sanvi_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialist_id", referencedColumnName = "id")
    private Specialist specialist;

    @Column(nullable = false)
    private Integer weekDay; // 1 = Segunda, ..., 7 = Domingo

    @Column(nullable = false)
    private LocalTime startTime;

    private LocalTime startInterval;
    private LocalTime endInterval;

    @Column(nullable = false)
    private LocalTime endTime;
}
