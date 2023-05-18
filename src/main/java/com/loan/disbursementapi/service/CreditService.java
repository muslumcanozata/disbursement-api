package com.loan.disbursementapi.service;

import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;

import java.math.BigDecimal;

public interface CreditService {
    Credit disburseCredit(int installmentCount, BigDecimal amount, User user);
}
