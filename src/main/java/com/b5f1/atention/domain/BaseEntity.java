package com.b5f1.atention.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public class BaseEntity {
    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted;

    public void deleted() {
        this.isDeleted = true;
    }
}