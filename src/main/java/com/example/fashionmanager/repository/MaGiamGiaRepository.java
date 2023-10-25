package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.MaGiamGiaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MaGiamGiaRepository extends JpaRepository<MaGiamGiaEntity,Long>, JpaSpecificationExecutor<MaGiamGiaEntity> {

    Page<MaGiamGiaEntity> findByMaVoucher(String maVoucher, PageRequest pageRequest);
}
