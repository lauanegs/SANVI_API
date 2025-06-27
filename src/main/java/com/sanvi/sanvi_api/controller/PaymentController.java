package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.controller.dto.AlertaDTO;
import com.sanvi.sanvi_api.controller.dto.DashboardSummaryDTO;
import com.sanvi.sanvi_api.controller.dto.PaymentByMonthDTO;
import com.sanvi.sanvi_api.domain.PaymentEntry;
import com.sanvi.sanvi_api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PutMapping("/{id}/pay")
    public PaymentEntry payInstallment(
            @PathVariable Long id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate paymentDate) {

        return paymentService.payInstallment(id, paymentDate);
    }


    @GetMapping("/monthly")
    public List<PaymentByMonthDTO> getMonthlyPaidValues() {
        return paymentService.getMonthlyPaidValues();
    }

    @GetMapping("/dashboard-summary")
    public DashboardSummaryDTO getDashboardSummary() {
        return paymentService.getDashboardSummary();
    }

    @GetMapping("/alerts")
    public List<AlertaDTO> getAlerts() {
        return paymentService.getMonthlyPendingAlerts();
    }

}
