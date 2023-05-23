package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.controller.request.DisbursementRequest;
import com.loan.disbursementapi.controller.response.DisbursementResponse;
import com.loan.disbursementapi.controller.response.InstallmentResponse;
import com.loan.disbursementapi.domain.dto.InstallmentDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.mapper.CoreMapper;
import com.loan.disbursementapi.service.CreditService;
import com.loan.disbursementapi.service.InstallmentService;
import com.loan.disbursementapi.service.UserService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DisbursementServiceImplTest {
    @InjectMocks
    private DisbursementServiceImpl service;
    @Mock
    private CreditService creditService;
    @Mock
    private InstallmentService installmentService;
    @Mock
    private UserService userService;
    @Mock
    private CoreMapper coreMapper;

    @Test
    public void disburse_whenItHasUserAndCredit_ThenSaveInstallments() {
        //given
        User user = Instancio.create(User.class);
        Credit credit = Instancio.create(Credit.class);
        InstallmentDTO installmentDTO = Instancio.create(InstallmentDTO.class);
        DisbursementRequest disbursementRequest = Instancio.create(DisbursementRequest.class);
        InstallmentResponse installmentResponse = Instancio.create(InstallmentResponse.class);
        DisbursementResponse expected = new DisbursementResponse();
        expected.setCreditId(credit.getId());
        expected.setInstallments(Collections.singletonList(installmentResponse));

        //when
        when(userService.getUser(Mockito.any(Integer.class))).thenReturn(user);
        when(creditService.disburseCredit(Mockito.anyInt(), Mockito.any(BigDecimal.class), Mockito.any(User.class))).thenReturn(credit);
        when(installmentService.initializeInstallments(Mockito.any(Credit.class))).thenReturn(Collections.singletonList(installmentDTO));
        when(coreMapper.toInstallmentResponse(installmentDTO)).thenReturn(installmentResponse);
        DisbursementResponse actual = service.disburse(disbursementRequest);

        //then
        then(actual.getCreditId()).isEqualTo(expected.getCreditId());
        then(actual.getInstallments().get(0).getAmount()).isEqualTo(expected.getInstallments().get(0).getAmount());
    }

    @Test
    public void disburse_whenUserNotFound_ThenReturnNull() {
        //given
        DisbursementRequest disbursementRequest = Instancio.create(DisbursementRequest.class);

        //when
        when(userService.getUser(Mockito.any(Integer.class))).thenReturn(null);

        DisbursementResponse actual = service.disburse(disbursementRequest);

        //then
        then(actual).isNull();
    }
}
