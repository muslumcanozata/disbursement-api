package com.loan.disbursementapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8917259933378580958L;
    @Id
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "COMMON_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @Column(name = "ID", updatable = false, nullable = false)
    private int id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Credit> credits;
}
