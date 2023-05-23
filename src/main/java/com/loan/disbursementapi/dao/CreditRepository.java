package com.loan.disbursementapi.dao;

import com.loan.disbursementapi.domain.entity.Credit;
import com.loan.disbursementapi.domain.entity.User;
import com.loan.disbursementapi.domain.enums.CreditStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer> {
    List<Credit> findAllByUser(User user);
    List<Credit> findAllByUser_IdAndStatusAndCreatedAt(Integer userId, CreditStatus creditStatus, LocalDate createdAt, Pageable pageable);
}
