package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.dao.InstallmentRepository;
import com.loan.disbursementapi.domain.dto.InstallmentDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.Installment;
import com.loan.disbursementapi.domain.enums.InstallmentStatus;
import com.loan.disbursementapi.service.InstallmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InstallmentServiceImpl implements InstallmentService {
    private final ModelMapper modelMapper;
    private final InstallmentRepository repository;

    @Override
    public List<InstallmentDTO> initializeInstallments(Credit credit) {
        BigDecimal installmentAmount = calculateInstallmentAmount(credit.getAmount(), credit.getInstallmentCount());
        BigDecimal totalInsertedInstallmentAmount = BigDecimal.ZERO;
        List<Installment> installments = new ArrayList<>();
        for (int i=0;i<credit.getInstallmentCount()-1;i++) {
            addInstallment(installments, credit, installmentAmount, false);
            totalInsertedInstallmentAmount = totalInsertedInstallmentAmount.add(installmentAmount);
        }
        BigDecimal lastInstallmentAmount = credit.getAmount().subtract(totalInsertedInstallmentAmount);
        addInstallment(installments, credit, lastInstallmentAmount, true);
        return insertInstallments(installments);
    }

    private void addInstallment(List<Installment> installments, Credit credit, BigDecimal installmentAmount, boolean isLast) {
        Installment installment = new Installment();
        installment.setAmount(installmentAmount);
        installment.setCredit(credit);
        installment.setDueDate(getValidDueDateOfInstallment(LocalDateTime.now(), installments.size()+1));
        installment.setStatus(InstallmentStatus.PENDING);
        installment.setLast(isLast);
        installments.add(installment);
    }

    private LocalDateTime getValidDueDateOfInstallment(LocalDateTime date, int installmentNumber) {
        date = date.plusDays(installmentNumber * 30L);
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        if(day == DayOfWeek.SATURDAY) {
            return date.plusDays(2);
        } else if( day == DayOfWeek.SUNDAY) {
            return date.plusDays(1);
        }
        return date;
    }

    private BigDecimal calculateInstallmentAmount(BigDecimal amount, int installmentCount) {
        return amount.divide(BigDecimal.valueOf(installmentCount), 2, RoundingMode.HALF_UP);
    }

    @Override
    public List<InstallmentDTO> insertInstallments(List<Installment> installments) {
        if(!CollectionUtils.isEmpty(installments)) {
            return modelMapper.map(repository.saveAll(installments), new TypeToken<List<InstallmentDTO>>() {}.getType());
        }
        return new ArrayList<>();
    }

    @Override
    public Installment getInstallmentById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public InstallmentDTO update(Installment installment) {
        return modelMapper.map(repository.save(installment), InstallmentDTO.class);
    }

    @Override
    public List<Installment> getAllOpenInstallmentsByCreditId(Integer creditId) {
        return repository.findAllByCreditIdAndStatus(creditId, InstallmentStatus.PAID);
    }
}
