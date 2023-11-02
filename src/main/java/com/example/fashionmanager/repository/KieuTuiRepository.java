package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.ChatLieuEntity;
import com.example.fashionmanager.entity.KieuDangEntity;
import com.example.fashionmanager.entity.KieuTuiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuTuiRepository extends JpaRepository<KieuTuiEntity, Long>, JpaSpecificationExecutor<KieuTuiEntity> {
    @Override
    long count();
}
