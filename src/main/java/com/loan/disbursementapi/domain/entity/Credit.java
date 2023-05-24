package com.loan.disbursementapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.loan.disbursementapi.domain.enums.CreditStatus;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "CREDITS")
public class Credit extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8917259933378580968L;
    @Id
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "COMMON_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "STATUS")
    private CreditStatus status;
    @Column(name="AMOUNT", columnDefinition="Decimal(10,2)", precision = 10, scale = 2)
    private BigDecimal amount;
    @Column(name = "INSTALLMENT_COUNT")
    private int installmentCount;
    @JsonBackReference
    @OneToMany(mappedBy = "credit", fetch = FetchType.LAZY)
    private List<Installment> installments;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
}