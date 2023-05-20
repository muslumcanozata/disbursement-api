package com.loan.disbursementapi.service;

import com.loan.disbursementapi.domain.dto.InstallmentDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.Installment;

import java.util.List;

public interface InstallmentService {
    List<InstallmentDTO> initializeInstallments(Credit credit);

    List<InstallmentDTO> insertInstallments(List<Installment> installments);

    Installment getInstallmentById(Integer id);

    InstallmentDTO update(Installment installment);

    List<Installment> getAllOpenInstallmentsByCreditId(Integer creditId);

    void checkOverdue();
}
