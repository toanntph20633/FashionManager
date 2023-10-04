package com.example.fashionmanager.repository;

import com.example.fashionmanager.entity.NhaCungCapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<NhaCungCapEntity, Long>, JpaSpecificationExecutor<NhaCungCapEntity>{
    boolean existsBySuppilerCodeAndDeleted(String suppilerCode,boolean deleted);
    boolean existsBySuppilerCodeAndDeletedAndIdNot( String code, Boolean delete, Long id);

}
