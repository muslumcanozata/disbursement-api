package com.loan.disbursementapi.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DisbursementResponse {
    private int creditId;
    private List<InstallmentResponse> installments;
}
