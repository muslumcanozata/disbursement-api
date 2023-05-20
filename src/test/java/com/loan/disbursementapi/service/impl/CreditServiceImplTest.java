package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.dao.CreditRepository;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.domain.enums.CreditStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreditServiceImplTest {
    @InjectMocks
    private CreditServiceImpl service;
    @Mock
    private CreditRepository repository;

    @Test
    public void disburseCreditTest() {
        //given
        int installmentCount = 1;
        BigDecimal amount = BigDecimal.valueOf(1);
        User user = new User();
        Credit credit = new Credit();
        credit.setUser(user);
        credit.setStatus(CreditStatus.OPEN);
        credit.setInstallmentCount(installmentCount);
        credit.setAmount(amount);
        credit.setId(1);

        //when
        when(repository.save(any(Credit.class))).thenReturn(credit);

        service.disburseCredit(installmentCount, amount, user);

        then(credit.getId()).isNotNull();
    }
}
