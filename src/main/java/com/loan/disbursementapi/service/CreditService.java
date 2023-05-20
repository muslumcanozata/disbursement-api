package com.loan.disbursementapi.service;

import com.loan.disbursementapi.domain.dto.CreditDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.domain.enums.CreditStatus;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface CreditService {
    Credit disburseCredit(int installmentCount, BigDecimal amount, User user);

    List<CreditDTO> getAllByUserId(Integer userId);

    List<CreditDTO> getAllByUserIdAndStatusAndDateWithPageable(Integer userId, CreditStatus status, LocalDateTime date, Pageable pageable);

    void closeCredit(Integer creditId);
}
