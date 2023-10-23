package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.NhanVienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVienEntity,Long>, JpaSpecificationExecutor<NhanVienEntity> {
    boolean existsByCccdAndDeleted(String cccd, Boolean delete);

    boolean existsByCccdAndDeletedAndIdNot(String cccd, Boolean delete, Long id);
}
