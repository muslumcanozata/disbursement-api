package com.loan.disbursementapi.dao;

import com.loan.disbursementapi.domain.entity.Installment;
import com.loan.disbursementapi.domain.enums.InstallmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Integer> {
    @Query(value = "SELECT i FROM Installment i WHERE i.credit.id = :creditId and i.status = :status")
    List<Installment> findAllByCreditIdAndStatus(Integer creditId, InstallmentStatus status);
}
