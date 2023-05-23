package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.controller.request.PaybackRequest;
import com.loan.disbursementapi.domain.dto.InstallmentDTO;
import com.loan.disbursementapi.domain.entity.Installment;
import com.loan.disbursementapi.domain.enums.InstallmentStatus;
import com.loan.disbursementapi.service.CreditService;
import com.loan.disbursementapi.service.InstallmentService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaybackServiceImplTest {
    @InjectMocks
    private PaybackServiceImpl service;
    @Mock
    private InstallmentService installmentService;
    @Mock
    private CreditService creditService;

    @Test
    public void payback_WhenAmountIsEqualToInstallmentAmount_ThenReturn() {
        //given
        Installment installment = Instancio.create(Installment.class);
        installment.setAmount(BigDecimal.TEN);
        installment.setStatus(InstallmentStatus.PAYABLE);
        InstallmentDTO expected = new InstallmentDTO();
        expected.setAmount(BigDecimal.ZERO);
        expected.setStatus(InstallmentStatus.PAID);
        PaybackRequest paybackRequest = new PaybackRequest();
        paybackRequest.setInstallmentId(installment.getId());
        paybackRequest.setAmount(installment.getAmount());

        List<Installment> installments = Instancio.ofList(Installment.class).size(2).create();

        //when
        when(installmentService.getInstallmentById(Mockito.anyInt())).thenReturn(installment);
        when(installmentService.getAllOpenInstallmentsByCreditId(installment.getCredit().getId())).thenReturn(installments);
        when(installmentService.update(Mockito.any(Installment.class))).thenReturn(expected);

        InstallmentDTO actual = service.payback(paybackRequest);

        //then
        then(actual.getAmount()).isEqualTo(expected.getAmount());
        then(actual.getStatus()).isEqualTo(expected.getStatus());
    }

    @Test
    public void payback_WhenAmountIsNotEqualToInstallmentAmount_ThenReturn() {
        //given
        Installment installment = Instancio.create(Installment.class);
        installment.setStatus(InstallmentStatus.PAYABLE);
        PaybackRequest paybackRequest = new PaybackRequest();
        paybackRequest.setInstallmentId(installment.getId());
        paybackRequest.setAmount(installment.getAmount().subtract(BigDecimal.ONE));
        InstallmentDTO expected = new InstallmentDTO();
        expected.setAmount(installment.getAmount().subtract(paybackRequest.getAmount()));
        expected.setStatus(installment.getStatus());

        //when
        when(installmentService.getInstallmentById(Mockito.anyInt())).thenReturn(installment);
        when(installmentService.getAllOpenInstallmentsByCreditId(installment.getCredit().getId())).thenReturn(new ArrayList<>());
        when(installmentService.update(Mockito.any(Installment.class))).thenReturn(expected);

        InstallmentDTO actual = service.payback(paybackRequest);

        //then
        then(actual.getAmount()).isEqualTo(expected.getAmount());
        then(actual.getStatus()).isEqualTo(expected.getStatus());
    }

}
