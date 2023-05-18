package com.loan.disbursementapi.dao;

import com.loan.disbursementapi.domain.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Integer> {
}
