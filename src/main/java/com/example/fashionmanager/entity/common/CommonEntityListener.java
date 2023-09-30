package com.example.fashionmanager.entity.common;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDate;

public class CommonEntityListener {
    @PrePersist
    private void prePersistCreate(CommonEntity entity) {

        entity.setDateCreate(LocalDate.now());
        entity.setDateUpdate(LocalDate.now());
        entity.setActive(true);
        entity.setDeleted(false);
    }

    @PreUpdate
    private void prePersistUpdate(CommonEntity entity) {

        entity.setDateUpdate(LocalDate.now());
    }
}
