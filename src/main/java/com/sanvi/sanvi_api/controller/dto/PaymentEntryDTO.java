package com.sanvi.sanvi_api.controller.dto;

import java.math.BigDecimal;

public class PaymentEntryDTO {

    private Long id;
    private BigDecimal value;
    private String status;

    public PaymentEntryDTO() {}

    public PaymentEntryDTO(Long id, BigDecimal value, String status) {
        this.id = id;
        this.value = value;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
