package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.mapper.CoreMapper;
import com.loan.disbursementapi.controller.request.DisbursementRequest;
import com.loan.disbursementapi.controller.response.DisbursementResponse;
import com.loan.disbursementapi.controller.response.InstallmentResponse;
import com.loan.disbursementapi.domain.dto.InstallmentDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.service.CreditService;
import com.loan.disbursementapi.service.DisbursementService;
import com.loan.disbursementapi.service.InstallmentService;
import com.loan.disbursementapi.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DisbursementServiceImpl implements DisbursementService {
    private final CreditService creditService;
    private final InstallmentService installmentService;
    private final UserService userService;
    private final CoreMapper coreMapper;

    @Override
    @Transactional
    public DisbursementResponse disburse(DisbursementRequest disbursementRequest) {
        User user = userService.getUser(disbursementRequest.getUserId());
        if(user != null) {
            Credit credit = creditService.disburseCredit(disbursementRequest.getInstallmentCount(), disbursementRequest.getAmount(), user);
            if(credit != null) {
                List<InstallmentDTO> installmentDTOs = installmentService.initializeInstallments(credit);
                return initializeDisbursementResponse(credit.getId(), installmentDTOs);
            }
        }
        return null;
    }

    private DisbursementResponse initializeDisbursementResponse(int creditId, List<InstallmentDTO> installmentDTOs) {
        DisbursementResponse disbursementResponse = new DisbursementResponse();
        disbursementResponse.setCreditId(creditId);
        List<InstallmentResponse> installmentResponses = new ArrayList<>();
        installmentDTOs.forEach(installmentDTO -> installmentResponses.add(coreMapper.toInstallmentResponse(installmentDTO)));
        disbursementResponse.setInstallments(installmentResponses);
        return disbursementResponse;
    }
}
