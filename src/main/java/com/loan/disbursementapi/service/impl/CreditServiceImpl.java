package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.dao.CreditRepository;
import com.loan.disbursementapi.domain.dto.CreditDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.domain.enums.CreditStatus;
import com.loan.disbursementapi.exception.custom.CreditNotFoundException;
import com.loan.disbursementapi.mapper.CoreMapper;
import com.loan.disbursementapi.service.CreditService;
import com.loan.disbursementapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
        List<Credit> credits = creditRepository.findAllByUser(user).orElseThrow(() -> new CreditNotFoundException(userId));
        return coreMapper.toCreditDTOs(credits);
    }

    @Override
    public List<CreditDTO> getAllByUserIdAndStatusAndDateWithPageable(Integer userId, CreditStatus status, LocalDate createdAt, Pageable pageable) {
        User user = userService.getUser(userId);
        List<Credit> credits = creditRepository.findAllByUser_IdAndStatusAndCreatedAt(user.getId(), status, createdAt, pageable).orElseThrow(() -> new CreditNotFoundException(userId));
        return coreMapper.toCreditDTOs(credits);
    }

    @Override
    public void closeCredit(Integer creditId) {
        Credit credit = creditRepository.findById(creditId).orElseThrow(CreditNotFoundException::new);
        credit.setStatus(CreditStatus.CLOSED);
        creditRepository.save(credit);
    }
}
