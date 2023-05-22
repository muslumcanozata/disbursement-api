package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.dao.CreditRepository;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.domain.enums.CreditStatus;
import com.loan.disbursementapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CreditServiceImplTest {
    @InjectMocks
    private CreditServiceImpl service;
    @Mock
    private CreditRepository repository;
    @Mock
    private UserService userService;
    @Mock
    private CreditRepository creditRepository;

    @Test
    public void disburseCredit_ThenSaveCredit() {
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

        //then
        then(credit.getId()).isNotNull();
    }
/*
    @Test
    public void getAllByUserId_whenUserNotFound_ThenReturnEmptyList() {
        //given
        Integer id = 0;

        //when
        when(userService.getUser(anyInt())).thenReturn(null);

        List<CreditDTO> creditDTOs = service.getAllByUserId(id);

        //then
        then(CollectionUtils.isEmpty(creditDTOs)).isTrue();
    }

    public void getAllByUserId_whenUserFound_ThenReturnEmptyList() {
        //given
        Integer id = 0;
        User user = Instancio.create(User.class);
        Credit credit = Instancio.create(Credit.class);
        CreditDTO creditDTO = Instancio.create(CreditDTO.class);

        //when
        when(userService.getUser(anyInt())).thenReturn(user);
        when(creditRepository.findAllByUser(any(User.class))).thenReturn(Collections.singletonList(credit));
        when(modelMapper.map(Mockito.any(),Mockito.any())).thenReturn(Collections.singletonList(creditDTO));
        //        when(creditRepository.findAllByUser(any(User.class), any(CreditStatus.class), any(LocalDateTime.class), any(Pageable.class))).thenReturn(Collections.singletonList(credit));
        List<CreditDTO> creditDTOs = service.getAllByUserId(id);

        //then
        then(creditDTOs.size()).isEqualTo(1);
        then(creditDTOs.get(0).getUser()).isEqualTo(creditDTO.getUser());
        then(creditDTOs.get(0).getId()).isEqualTo(creditDTO.getId());

    }
    */
}
