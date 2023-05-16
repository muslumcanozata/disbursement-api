package com.loan.disbursementapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Column(name = "UPDATED_AT")
    private Timestamp updatedAt;
    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @PrePersist
    public void prePersist() {
        setCreatedAt(Timestamp.from(Instant.now()));
        setUpdatedAt(Timestamp.from(Instant.now()));
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(Timestamp.from(Instant.now()));
    }
}
