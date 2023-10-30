package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.DanhMucEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMucEntity, Long>, JpaSpecificationExecutor<DanhMucEntity> {
    @Override
    long count();
}
