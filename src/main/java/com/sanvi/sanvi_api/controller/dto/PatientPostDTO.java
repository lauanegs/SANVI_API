package com.sanvi.sanvi_api.controller.dto;

import com.sanvi.sanvi_api.domain.enums.Gender;
import com.sanvi.sanvi_api.domain.enums.UF;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * DTO para criar um novo Paciente e seu Prontuário Médico inicial em uma única requisição.
 * Este objeto é recebido no corpo (body) da requisição POST.
 */
@Getter
@Setter
public class PatientPostDTO {

    // --- Campos da classe Person ---
    private String name;
    private String cpf;
    private Date birthDate;
    private Long phoneNumber;
    private String address;
    private String cep;
    private int addressNumber;
    private String neighborhood;
    private UF uf;
    private Gender gender;
    private String rg;
    private String guardianName;
    private String guardianCPF;
    private Long guardianPhoneNumber;

    // --- Campo da classe Patient ---
    private String profession;

    // --- Dados do Prontuário Médico ---
    // Este campo pode ser opcional. Se o cliente enviar, o prontuário é criado.
    // Se for nulo, apenas o paciente é criado.
    private MedicalRecordPostDTO medicalRecord; 
}