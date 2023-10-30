package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.LoaiSanPhamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPhamEntity, Long>, JpaSpecificationExecutor<LoaiSanPhamEntity> {
    @Override
    long count();
}
