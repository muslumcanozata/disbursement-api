package com.loan.disbursementapi.service;

import com.loan.disbursementapi.controller.request.PaybackRequest;
import com.loan.disbursementapi.domain.dto.InstallmentDTO;

public interface PaybackService {
    InstallmentDTO payback(PaybackRequest paybackRequest);
}
