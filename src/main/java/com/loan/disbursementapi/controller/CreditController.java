package com.loan.disbursementapi.controller;

import com.loan.disbursementapi.controller.request.GetCreditsWithPaginationAndFilterRequest;
import com.loan.disbursementapi.domain.constant.Constants;
import com.loan.disbursementapi.domain.dto.CreditDTO;
import com.loan.disbursementapi.service.CreditService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/credits")
public class CreditController {
    private final CreditService creditService;
    @GetMapping("/{user-id}")
    @Transactional
    public ResponseEntity<List<CreditDTO>> getAllByUserId(@PathVariable("user-id") Integer userId) {
        return new ResponseEntity<>(creditService.getAllByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/page/{user-id}")
    public ResponseEntity<List<CreditDTO>> getAllByUserId(@PathVariable("user-id") Integer userId, @RequestBody GetCreditsWithPaginationAndFilterRequest request) {
        Pageable pageable = PageRequest.of(request.getPage()-Constants.ONE, request.getSize());
        return new ResponseEntity<>(creditService.getAllByUserIdAndStatusAndDateWithPageable(userId, request.getStatus(), request.getCreatedAt(), pageable), HttpStatus.OK);
    }
}
