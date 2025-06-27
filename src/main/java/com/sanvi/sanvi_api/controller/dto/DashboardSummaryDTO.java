package com.sanvi.sanvi_api.controller.dto;

import java.math.BigDecimal;

public class DashboardSummaryDTO {
     private BigDecimal totalCaixa;
    private BigDecimal recebidoMesAtual;
    private BigDecimal recebidoMesAnterior;

    public DashboardSummaryDTO(BigDecimal totalCaixa, BigDecimal recebidoMesAtual, BigDecimal recebidoMesAnterior) {
        this.totalCaixa = totalCaixa;
        this.recebidoMesAtual = recebidoMesAtual;
        this.recebidoMesAnterior = recebidoMesAnterior;
    }

    public BigDecimal getTotalCaixa() {
        return totalCaixa;
    }

    public BigDecimal getRecebidoMesAtual() {
        return recebidoMesAtual;
    }

    public BigDecimal getRecebidoMesAnterior() {
        return recebidoMesAnterior;
    }
}
