package com.sanvi.sanvi_api.domain;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MedicalRecordData implements Serializable {

    private String pergunta1;
    private String pergunta2;
    private String pergunta3;
}
