package com.sanvi.sanvi_api.controller.dto;

import java.math.BigDecimal;

public class PaymentByMonthDTO {
        private String month;
    private BigDecimal total;

    public PaymentByMonthDTO() {
        
    }

     public PaymentByMonthDTO(String month, BigDecimal total) {
        this.month = month;
        this.total = total;
    }

 

    // getters e setters
    
    public String getMonth() {
        return month;
    }

    public BigDecimal getTotal() {
        return total;
    }
}


