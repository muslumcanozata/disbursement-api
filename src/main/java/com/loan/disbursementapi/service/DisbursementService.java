package com.loan.disbursementapi.service;

import com.loan.disbursementapi.controller.request.DisbursementRequest;
import com.loan.disbursementapi.controller.response.DisbursementResponse;

public interface DisbursementService {
    DisbursementResponse disburse(DisbursementRequest disbursementRequest);
}
