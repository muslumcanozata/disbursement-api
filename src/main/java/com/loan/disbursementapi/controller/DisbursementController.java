package com.loan.disbursementapi.controller;

import com.loan.disbursementapi.controller.request.DisbursementRequest;
import com.loan.disbursementapi.controller.response.DisbursementResponse;
import com.loan.disbursementapi.service.DisbursementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/disbursement")
public class DisbursementController {
    private final DisbursementService disbursementService;
    @GetMapping
    public ResponseEntity<DisbursementResponse> disburse(DisbursementRequest request) {
        DisbursementResponse disbursementResponse = disbursementService.disburse(request);
        if(disbursementResponse != null) {
            return new ResponseEntity<>(disbursementResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
