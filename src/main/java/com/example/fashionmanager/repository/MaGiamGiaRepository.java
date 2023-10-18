package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.MaGiamGiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MaGiamGiaRepository extends JpaRepository<MaGiamGiaEntity,Long>, JpaSpecificationExecutor<MaGiamGiaEntity> {
    boolean existsByMaVoucherAndDeleted(String maVoucher, Boolean delete);

    boolean existsByMaVoucherAndDeletedAndIdNot(String maVoucher, Boolean delete, Long id);
}