package com.loan.disbursementapi.service.impl;

import com.loan.disbursementapi.dao.InstallmentRepository;
import com.loan.disbursementapi.domain.dto.InstallmentDTO;
import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.Installment;
import com.loan.disbursementapi.domain.enums.InstallmentStatus;
import com.loan.disbursementapi.mapper.CoreMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InstallmentServiceImplTest {
    @InjectMocks
    private InstallmentServiceImpl service;
    @Mock
    private InstallmentRepository repository;
    @Mock
    private CoreMapper coreMapper;

    @Test
    public void initializeInstallments_ThenInsertInstallments() {
        //given
        Credit credit = Instancio.create(Credit.class);
        List<Installment> installments = Instancio.ofList(Installment.class).size(10).create();
        credit.setInstallmentCount(installments.size());
        List<InstallmentDTO> expected = Instancio.ofList(InstallmentDTO.class).size(10).create();

        //when
        when(repository.saveAll(Mockito.anyList())).thenReturn(installments);
        when(coreMapper.toInstallmentDTOs(Mockito.anyList())).thenReturn(expected);

        List<InstallmentDTO> actual = service.initializeInstallments(credit);

        //then
        verify(repository, times(1)).saveAll(Mockito.anyList());
        then(actual.size()).isEqualTo(expected.size());
        then(actual.get(0).getAmount()).isEqualTo(expected.get(0).getAmount());
    }

    @Test
    public void insertInstallments_WhenListIsEmpty_ThenReturnEmptyList() {
        List<InstallmentDTO> actual = service.insertInstallments(new ArrayList<>());

        //then
        verify(repository, times(0)).saveAll(Mockito.anyList());
        then(actual.size()).isEqualTo(0);
    }

    @Test
    public void getInstallmentById_ThenReturn() {
        //given
        Installment expected = Instancio.create(Installment.class);

        //when
        when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(expected));

        Installment actual = service.getInstallmentById(expected.getId());

        //then
        verify(repository, times(1)).findById(Mockito.anyInt());
        then(actual.getId()).isEqualTo(expected.getId());
    }

    @Test
    public void update_ThenReturn() {
        //given
        Installment installment = Instancio.create(Installment.class);
        InstallmentDTO expected = Instancio.create(InstallmentDTO.class);

        //when
        when(repository.save(Mockito.any(Installment.class))).thenReturn(installment);
        when(coreMapper.toInstallmentDTO(Mockito.any(Installment.class))).thenReturn(expected);

        InstallmentDTO actual = service.update(installment);

        //then
        verify(repository, times(1)).save(Mockito.any(Installment.class));
        then(actual.getId()).isEqualTo(expected.getId());
    }

    @Test
    public void getAllOpenInstallmentsByCreditId_ThenReturn() {
        //given
        List<Installment> expected = Instancio.ofList(Installment.class).size(2).create();

        //when
        when(repository.findAllByCreditIdAndStatusIsNot(Mockito.anyInt(), Mockito.any(InstallmentStatus.class))).thenReturn(Optional.of(expected));

        List<Installment> actual = service.getAllOpenInstallmentsByCreditId(0);

        //then
        verify(repository, times(1)).findAllByCreditIdAndStatusIsNot(Mockito.anyInt(), Mockito.any(InstallmentStatus.class));
        then(actual.get(0).getId()).isEqualTo(expected.get(0).getId());
    }

    @Test
    public void checkOverdue_ThenReturn() {
        //given
        LocalDate before20days = LocalDate.now().minusDays(20);
        LocalDate before5days = LocalDate.now().minusDays(5);
        List<Installment> expected = Instancio.ofList(Installment.class).size(3).create();
        expected.get(0).setDueDate(before5days);
        expected.get(1).setDueDate(before20days);

        //when
        when(repository.findAllByStatusIsNot(Mockito.any(InstallmentStatus.class))).thenReturn(Optional.of(expected));

        service.checkOverdue();

        //then
        verify(repository, times(1)).saveAll(Mockito.anyList());
    }
}
