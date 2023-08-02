package com.b5f1.atention.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public class BaseEntity {
    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    public void deleted() {
        this.isDeleted = true;
    }
}