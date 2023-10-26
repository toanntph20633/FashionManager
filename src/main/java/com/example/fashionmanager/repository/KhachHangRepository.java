package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.KhachHangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHangEntity, Long>, JpaSpecificationExecutor<KhachHangEntity> {
    boolean existsByMaKhachHangAndDeleted(String ma, Boolean delete);
    boolean existsByMaKhachHangAndDeletedAndIdIsNot(String ma,Boolean delete,Long id);
}
