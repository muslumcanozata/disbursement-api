package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.controller.request.PaybackRequest;
import com.loan.disbursementapi.domain.constant.Constants;
import com.loan.disbursementapi.domain.dto.InstallmentDTO;
import com.loan.disbursementapi.domain.entity.Installment;
import com.loan.disbursementapi.domain.enums.InstallmentStatus;
import com.loan.disbursementapi.service.CreditService;
import com.loan.disbursementapi.service.InstallmentService;
import com.loan.disbursementapi.service.PaybackService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaybackServiceImpl implements PaybackService {
    private final InstallmentService installmentService;
    private final CreditService creditService;

    @Override
    @Transactional
    public InstallmentDTO payback(PaybackRequest paybackRequest) {
        Installment installment = installmentService.getInstallmentById(paybackRequest.getInstallmentId());
        InstallmentDTO installmentDTO = new InstallmentDTO();
        if(installment != null && InstallmentStatus.isActive(installment.getStatus()) && installment.getAmount() != null) {
            if(installment.getAmount().compareTo(paybackRequest.getAmount()) == Constants.ZERO) {
                installment.setAmount(BigDecimal.ZERO);
                installment.setStatus(InstallmentStatus.PAID);
                installmentDTO = installmentService.update(installment);
            } else if(installment.getAmount().compareTo(paybackRequest.getAmount()) == Constants.ONE) {
                installment.setAmount(installment.getAmount().subtract(paybackRequest.getAmount()));
                installmentDTO = installmentService.update(installment);
            } if(installment.getCredit() != null) {
                checkInstallmentsToCloseCredit(installment.getCredit().getId());
            }
        }
        return installmentDTO;
    }

    @Async
    public void checkInstallmentsToCloseCredit(Integer creditId) {
        List<Installment> installmentDTOs = installmentService.getAllOpenInstallmentsByCreditId(creditId);
        if(CollectionUtils.isEmpty(installmentDTOs)) {
            creditService.closeCredit(creditId);
        }
    }
}
