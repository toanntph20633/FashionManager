package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.LopLotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LopLotRepository extends JpaRepository<LopLotEntity,Long>, JpaSpecificationExecutor<LopLotEntity> {
    @Override
    long count();
}
