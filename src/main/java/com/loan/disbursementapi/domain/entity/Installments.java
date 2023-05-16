package com.loan.disbursementapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "INSTALLMENTS")
public class Installments extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8917259933378580978L;
    @Id
    @SequenceGenerator(name = "INSTALLMENTS_SEQ", sequenceName = "COMMON_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSTALLMENTS_SEQ")
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
    @Column(name="AMOUNT", columnDefinition="Decimal(10,2)", precision = 10, scale = 2)
    private BigDecimal amount;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Credit credit;
    @Column(name = "STATUS")
    private Integer status;
}