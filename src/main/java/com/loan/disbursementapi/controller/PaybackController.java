package com.loan.disbursementapi.controller;

import com.loan.disbursementapi.controller.request.PaybackRequest;
import com.loan.disbursementapi.domain.dto.InstallmentDTO;
import com.loan.disbursementapi.service.PaybackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payback")
public class PaybackController {
    private final PaybackService paybackService;

    @PatchMapping
    public ResponseEntity<InstallmentDTO> payback(PaybackRequest paybackRequest) {
        return new ResponseEntity<>(paybackService.payback(paybackRequest), HttpStatus.NO_CONTENT);
    }
}
