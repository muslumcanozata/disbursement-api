package com.loan.disbursementapi.service.impl;


import com.loan.disbursementapi.dao.CreditRepository;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.domain.enums.CreditStatus;
import com.loan.disbursementapi.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;

    @Override
    public Credit disburseCredit(int installmentCount, BigDecimal amount, User user) {
        Credit credit = new Credit();
        credit.setAmount(amount);
        credit.setUser(user);
        credit.setInstallmentCount(installmentCount);
        credit.setStatus(CreditStatus.OPEN);
        return creditRepository.save(credit);
    }

}
