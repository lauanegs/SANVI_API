package com.sanvi.sanvi_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 150)
    private String username;
    @Column(length = 150)
    private String password;
    private String name;
    private short accessLevel;
}
