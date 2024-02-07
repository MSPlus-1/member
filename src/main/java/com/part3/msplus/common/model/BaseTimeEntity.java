package com.part3.msplus.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import org.hibernate.annotations.SQLRestriction;

import java.time.ZonedDateTime;

import static java.time.ZonedDateTime.now;

@MappedSuperclass
@SQLRestriction(value = "deleted_at is null")
@Getter
public abstract class BaseTimeEntity {

    @Column(updatable = false, nullable = false, name = "created_at")
    private ZonedDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

    @PrePersist
    protected void prePersist() {
        this.createdAt = now();
        this.updatedAt = now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = now();
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }
}
