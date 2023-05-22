package com.loan.disbursementapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Column(name = "UPDATED_AT")
    private LocalDate updatedAt;
    @Column(name = "CREATED_AT")
    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        setCreatedAt(LocalDate.now());
        setUpdatedAt(LocalDate.now());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(LocalDate.now());
    }
}
