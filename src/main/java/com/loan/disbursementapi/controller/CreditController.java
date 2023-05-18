package com.loan.disbursementapi.controller;

import com.loan.disbursementapi.domain.dto.CreditDTO;
import com.loan.disbursementapi.service.CreditService;
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
    public ResponseEntity<List<CreditDTO>> getAllByUserId(@PathVariable("user-id") Integer userId) {
        return new ResponseEntity<>(creditService.getAllByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/page/{user-id}")
    public ResponseEntity<List<CreditDTO>> getAllByUserId(@PathVariable("user-id") Integer userId, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(creditService.getAllByUserIdWithPageable(userId, pageable), HttpStatus.OK);
    }

}
