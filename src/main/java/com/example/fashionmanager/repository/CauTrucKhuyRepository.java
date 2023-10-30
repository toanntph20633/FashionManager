package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.CauTrucKhuyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CauTrucKhuyRepository extends JpaRepository<CauTrucKhuyEntity,Long>, JpaSpecificationExecutor<CauTrucKhuyEntity> {
    @Override
    long count();
}
