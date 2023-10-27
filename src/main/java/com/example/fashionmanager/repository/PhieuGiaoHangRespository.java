package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.PhieuGiaoHangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PhieuGiaoHangRespository extends JpaRepository<PhieuGiaoHangEntity, Long>, JpaSpecificationExecutor<PhieuGiaoHangEntity> {
    boolean existsByMaPhieuGiaoAndDeleted(String ma,Boolean delete);
    boolean existsByMaPhieuGiaoAndDeletedAndIdNot(String ma, Boolean delete, Long id);
}
