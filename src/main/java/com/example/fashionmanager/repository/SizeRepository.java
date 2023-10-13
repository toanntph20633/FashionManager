package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.KichThuocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SizeRepository extends JpaRepository<KichThuocEntity, Long>, JpaSpecificationExecutor<KichThuocEntity> {
//    boolean existsBySizeCodeAndDeleted(String codeSize, Boolean delete);
//
//    boolean existsBySizeCodeAndDeletedAndIdNot(String codeSize, Boolean delete, Long id);
}
