package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.entity.KieuDetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuDetResponsitory extends JpaRepository<KieuDetEntity,Long>, JpaSpecificationExecutor<KieuDetEntity> {
    @Override
    long count();
}
