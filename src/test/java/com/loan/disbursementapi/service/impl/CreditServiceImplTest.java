package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.dao.CreditRepository;
import com.loan.disbursementapi.domain.dto.CreditDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.domain.enums.CreditStatus;
import com.loan.disbursementapi.exception.custom.CreditNotFoundException;
import com.loan.disbursementapi.exception.custom.UserNotFoundException;
import com.loan.disbursementapi.mapper.CoreMapper;
import com.loan.disbursementapi.service.UserService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CreditServiceImplTest {
    @InjectMocks
    private CreditServiceImpl service;
    @Mock
    private UserService userService;
    @Mock
    private CreditRepository creditRepository;
    @Mock
    private CoreMapper coreMapper;

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
        when(creditRepository.save(any(Credit.class))).thenReturn(credit);

        service.disburseCredit(installmentCount, amount, user);

        //then
        then(credit.getId()).isNotNull();
    }

    @Test
    public void getAllByUserId_whenUserNotFound_ThenThrowError() {
        //given
        Integer id = 0;

        //when
        when(userService.getUser(anyInt())).thenThrow(new UserNotFoundException(id));

        //then
        Assertions.assertThrows(UserNotFoundException.class, () -> service.getAllByUserId(id));
    }

    @Test
    public void getAllByUserId_whenUserFound_ThenReturnCreditList() {
        //given
        Integer id = 0;
        User user = Instancio.create(User.class);
        CreditDTO creditDTO = Instancio.create(CreditDTO.class);
        List<Credit> credits = Collections.singletonList(coreMapper.toCredit(creditDTO));

        //when
        when(userService.getUser(Mockito.anyInt())).thenReturn(user);
        when(creditRepository.findAllByUser(Mockito.any(User.class))).thenReturn(Optional.of(credits));
        when(coreMapper.toCreditDTOs(Mockito.any())).thenReturn(Collections.singletonList(creditDTO));

        List<CreditDTO> creditDTOs = service.getAllByUserId(id);

        //then
        then(creditDTOs.size()).isEqualTo(1);
        then(creditDTOs.get(0).getId()).isEqualTo(creditDTO.getId());
    }

    @Test
    public void getAllByUserIdAndStatusAndDateWithPageable_whenUserNotFound_ThenThrowError() {
        //given
        Integer id = 0;
        CreditStatus creditStatus = CreditStatus.OPEN;

        //when
        when(userService.getUser(anyInt())).thenThrow(new UserNotFoundException(id));

        //then
        Assertions.assertThrows(UserNotFoundException.class, () -> service.getAllByUserIdAndStatusAndDateWithPageable(id, creditStatus, LocalDate.now(), Pageable.unpaged()));
    }

    @Test
    public void getAllByUserIdAndStatusAndDateWithPageable_whenUserFound_ThenReturnCreditList() {
        //given
        int id = 0;
        Credit credit = Instancio.create(Credit.class);
        User user = Instancio.create(User.class);
        user.setId(id);
        user.setCredits(Collections.singletonList(credit));
        CreditDTO creditDTO = Instancio.create(CreditDTO.class);

        //when
        when(userService.getUser(anyInt())).thenReturn(user);
        when(coreMapper.toCreditDTOs(Mockito.any())).thenReturn(Collections.singletonList(creditDTO));
        when(creditRepository.findAllByUser_IdAndStatusAndCreatedAt(anyInt(), any(CreditStatus.class), any(LocalDate.class), any(Pageable.class))).thenReturn(Optional.of(Collections.singletonList(credit)));

        List<CreditDTO> creditDTOs = service.getAllByUserIdAndStatusAndDateWithPageable(user.getId(), credit.getStatus(), LocalDate.now(), Pageable.unpaged());

        //then
        then(creditDTOs.size()).isEqualTo(1);
        then(creditDTOs.get(0).getId()).isEqualTo(creditDTO.getId());
    }

    @Test
    public void closeCredit_whenCreditFound_ThenSaveCredit() {
        //given
        Credit credit = Instancio.create(Credit.class);
        Optional<Credit> creditOptional = Optional.of(credit);

        //when
        when(creditRepository.findById(credit.getId())).thenReturn(creditOptional);

        service.closeCredit(credit.getId());

        //then
        verify(creditRepository, times(1)).save(any());
    }

    @Test
    public void closeCredit_whenCreditNotFound_ThenThrowError() {
        //given
        Integer id = 0;
        Optional<Credit> creditOptional = Optional.empty();

        //when
        when(creditRepository.findById(any())).thenReturn(creditOptional);

        //then
        Assertions.assertThrows(CreditNotFoundException.class, () -> service.closeCredit(id));
        verify(creditRepository, times(0)).save(any());
    }
}
