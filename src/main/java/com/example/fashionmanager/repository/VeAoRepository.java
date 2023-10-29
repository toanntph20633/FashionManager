package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.MauSacEntity;
import com.example.fashionmanager.entity.VeAoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VeAoRepository extends JpaRepository<VeAoEntity, Long>, JpaSpecificationExecutor<VeAoEntity> {
    @Override
    long count();
}
