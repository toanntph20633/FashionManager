package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.XetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface XeTaRepository extends JpaRepository<XetaEntity, Long>, JpaSpecificationExecutor<XetaEntity> {
    @Override
    long count();
}