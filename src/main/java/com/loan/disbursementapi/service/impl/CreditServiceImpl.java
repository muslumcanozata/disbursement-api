package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.dao.CreditRepository;
import com.loan.disbursementapi.domain.dto.CreditDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.domain.enums.CreditStatus;
import com.loan.disbursementapi.mapper.CoreMapper;
import com.loan.disbursementapi.service.CreditService;
import com.loan.disbursementapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final UserService userService;
    private final CoreMapper coreMapper;

    @Override
    public Credit disburseCredit(int installmentCount, BigDecimal amount, User user) {
        Credit credit = new Credit();
        credit.setAmount(amount);
        credit.setUser(user);
        credit.setInstallmentCount(installmentCount);
        credit.setStatus(CreditStatus.OPEN);
        return creditRepository.save(credit);
    }

    @Override
    public List<CreditDTO> getAllByUserId(Integer userId) {
        User user = userService.getUser(userId);
        if(user != null) {
            List<Credit> credits = creditRepository.findAllByUser(user);
            return coreMapper.toCreditDTOs(credits);
        }
        return new ArrayList<>();
    }

    @Override
    public List<CreditDTO> getAllByUserIdAndStatusAndDateWithPageable(Integer userId, CreditStatus status, LocalDate createdAt, Pageable pageable) {
        User user = userService.getUser(userId);
        if(user != null) {
            List<Credit> credits = creditRepository.findAllByUserAndStatusAndCreatedAt(user, status, createdAt, pageable);
            return coreMapper.toCreditDTOs(credits);
        }
        return new ArrayList<>();
    }

    @Override
    public void closeCredit(Integer creditId) {
        Optional<Credit> creditOptional = creditRepository.findById(creditId);
        if(creditOptional.isPresent()) {
            Credit credit = creditOptional.get();
            credit.setStatus(CreditStatus.CLOSED);
            creditRepository.save(credit);
        }
    }
}
