package com.loan.disbursementapi.controller.request;

import com.loan.disbursementapi.domain.enums.CreditStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetCreditsWithPaginationAndFilterRequest {
    Integer page;
    Integer size;
    CreditStatus status;
    LocalDate createdAt;
}
